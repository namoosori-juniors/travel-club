package io.namoosori.travelclub.phase7.aggregate.club.logic;

import io.namoosori.travelclub.phase7.aggregate.club.service.MembershipService;
import io.namoosori.travelclub.phase7.aggregate.club.store.MembershipStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.RoleInClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMembershipException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {
    //
    private MembershipStore membershipStore;

    public MembershipServiceLogic(MembershipStore membershipStore) {
        //
        this.membershipStore = membershipStore;
    }

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        //
        String clubId = membershipCdo.getClubId();
        String memberId = membershipCdo.getMemberId();
        RoleInClub role = membershipCdo.getRole();

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
        Membership membership = membershipStore.retrieve(clubId, memberId);
        return membership;
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        //
        List<Membership> membershipList = membershipStore.retrieveByClubId(clubId, false);
        return membershipList;
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberId) {
        //
        List<Membership> membershipList = membershipStore.retrieveByMemberId(memberId, false);
        return membershipList;
    }

    @Override
    public void modifyMembership(MembershipCdo membershipCdo) {
        //
        String clubId = membershipCdo.getClubId();
        String memberId = membershipCdo.getMemberId();
        RoleInClub role = membershipCdo.getRole();

        Membership membership = findMembershipIn(clubId, memberId);
        membership.setRole(role);

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
