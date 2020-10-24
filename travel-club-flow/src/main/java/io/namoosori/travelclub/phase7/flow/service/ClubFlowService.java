package io.namoosori.travelclub.phase7.flow.service;

import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;

public interface ClubFlowService {
    //
    String registerTravelClub(TravelClubCdo travelClubCdo);
    String registerMembership(MembershipCdo membershipCdo);
}
