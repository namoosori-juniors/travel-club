package io.namoosori.travelclub.phase7.spec.facade.aggregate;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;

import java.util.List;

public interface MembershipFacade {
    //
    String registerMembership(MembershipCdo membershipCdo);
    Membership findMembershipIn(String clubId, String memberId);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    void modifyMembership(MembershipCdo membershipCdo);
    void removeMembership(String clubId, String memberId);

}
