package io.namoosori.travelclub.spring.logic;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.aggregate.club.Membership;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.spring.service.MembershipService;
import io.namoosori.travelclub.spring.service.sdo.MembershipCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.store.MemberStore;
import io.namoosori.travelclub.spring.store.MembershipStore;
import io.namoosori.travelclub.spring.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;

import java.util.List;

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
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberId) {
        //
        return membershipStore.retrieve(clubId, memberId);
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
    public void modifyMembership(String clubId, String memberId, NameValueList nameValueList) {
        //
        Membership membership = membershipStore.retrieve(clubId, memberId);
        membership.modifyValues(nameValueList);

        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String clubId, String memberId) {
        //
        membershipStore.delete(clubId, memberId);
    }
}
