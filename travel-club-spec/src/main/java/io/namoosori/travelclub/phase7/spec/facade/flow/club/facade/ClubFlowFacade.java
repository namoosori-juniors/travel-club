package io.namoosori.travelclub.phase7.spec.facade.flow.club.facade;

import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;

public interface ClubFlowFacade {
    //
    String registerTravelClub(TravelClubCdo travelClubCdo);
    String registerMembership(MembershipCdo membershipCdo);
}
