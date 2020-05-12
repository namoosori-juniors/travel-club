package io.namoosori.travelclub.phase7.spec.facade.aggregate;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;

import java.util.List;

public interface TravelClubFacade {
    //
    String registerTravelClub(TravelClubCdo travelClubCdo);
    TravelClub findTravelClubById(String clubId);
    List<TravelClub> findTravelClubsByName(String clubName, boolean foundationTimeDescending);
    void modifyTravelClub(String clubId, NameValueList nameValues);
}
