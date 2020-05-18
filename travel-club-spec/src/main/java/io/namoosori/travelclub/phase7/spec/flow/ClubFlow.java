package io.namoosori.travelclub.phase7.spec.flow;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;

import java.util.List;

public interface ClubFlow {
    //
    String registerTravelClub(TravelClubCdo travelClubCdo);
    String registerMembership(MembershipCdo membershipCdo);
    Membership findMembershipIn(String clubUsid, String memberEmail);
    List<Membership> findAllMembershipsOfClub(String clubUsid);
    List<Membership> findAllMembershipsOfMember(String memberEmail);
}
