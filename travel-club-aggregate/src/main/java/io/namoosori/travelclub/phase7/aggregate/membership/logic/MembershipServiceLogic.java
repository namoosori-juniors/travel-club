package io.namoosori.travelclub.phase7.aggregate.membership.logic;

import io.namoosori.travelclub.phase7.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.phase7.aggregate.member.store.MemberStore;
import io.namoosori.travelclub.phase7.aggregate.membership.service.MembershipService;
import io.namoosori.travelclub.phase7.aggregate.membership.store.MembershipStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.phase7.spec.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchClubException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMembershipException;
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

        Membership membership = findMembershipIn(clubId, memberId);

        if (membership != null) {
            throw new MembershipDuplicationException("Member already exists in the club");
        }

        membership = new Membership(clubId, memberId);
        membership.setRole(role);

        String membershipId = membershipStore.create(membership);

        return membershipId;
    }

    @Override
    public Membership findMembershipIn(String clubId, String memberId) {
        //
        return membershipStore.retrieve(clubId, memberId);
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        //
        return membershipStore.retrieveByClubId(clubId, false);
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberId) {
        //
        return membershipStore.retrieveByMemberId(memberId, false);
    }

    @Override
    public void modifyMembership(String clubId, String memberId, NameValueList nameValueList) {
        //
        Membership membership = findMembershipIn(clubId, memberId);
        membership.modifyValues(nameValueList);

        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String clubId, String memberId) {
        //
        if (!membershipStore.exists(clubId, memberId)) {
            throw new NoSuchMembershipException("No such membership");
        }

        membershipStore.delete(clubId, memberId);
    }
}
