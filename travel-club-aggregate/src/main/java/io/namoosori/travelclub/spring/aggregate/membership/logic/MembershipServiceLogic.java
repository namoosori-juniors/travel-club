package io.namoosori.travelclub.spring.aggregate.membership.logic;

import io.namoosori.travelclub.spring.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.spring.aggregate.member.store.MemberStore;
import io.namoosori.travelclub.spring.aggregate.membership.service.MembershipService;
import io.namoosori.travelclub.spring.aggregate.membership.store.MembershipStore;
import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.aggregate.club.Membership;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.spring.spec.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.spring.spec.util.exception.NoSuchClubException;
import io.namoosori.travelclub.spring.spec.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.spring.spec.util.exception.NoSuchMembershipException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {
    //
    private MembershipStore membershipStore;
    private ClubStore clubStore;
    private MemberStore memberStore;

    public MembershipServiceLogic(MembershipStore membershipStore,
                                  ClubStore clubStore,
                                  MemberStore memberStore) {
        //
        this.membershipStore = membershipStore;
        this.clubStore = clubStore;
        this.memberStore = memberStore;
    }

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        //
        String clubId = membershipCdo.getClubId();
        String memberId = membershipCdo.getMemberId();
        RoleInClub role = membershipCdo.getRole();

        TravelClub club = clubStore.retrieve(clubId);

        if (club == null) {
            throw new NoSuchClubException("No such club with id " + clubId);
        }

        CommunityMember member = memberStore.retrieve(memberId);

        if (member == null) {
            throw new NoSuchMemberException("No such member with id " + memberId);
        }

        Membership membership = findMembershipByClubIdAndMemberId(clubId, memberId);

        if (membership != null) {
            throw new MembershipDuplicationException("Member already exists in the club");
        }

        membership = new Membership(clubId, memberId);
        membership.setRole(role);

        String membershipId = membershipStore.create(membership);

        return membershipId;
    }

    @Override
    public Membership findMembership(String membershipId) {
        //
        return membershipStore.retrieve(membershipId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberId) {
        //
        return membershipStore.retrieveByClubIdAndMemberId(clubId, memberId);
    }

    @Override
    public Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail) {
        //
        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with email " + memberEmail);
        }

        Membership membership = findMembershipByClubIdAndMemberId(clubId, member.getId());
        return membership;
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        //
        return membershipStore.retrieveByClubId(clubId);
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberId) {
        //
        return membershipStore.retrieveByMemberId(memberId);
    }

    @Override
    public void modifyMembership(String membershipId, NameValueList nameValueList) {
        //
        Membership membership = membershipStore.retrieve(membershipId);

        if (membership == null) {
            throw new NoSuchMembershipException("No such membership with id " + membershipId);
        }

        membership.modifyValues(nameValueList);

        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String membershipId) {
        //
        if (!membershipStore.exists(membershipId)) {
            throw new NoSuchMembershipException("No such membership with id " + membershipId);
        }

        membershipStore.delete(membershipId);
    }
}
