package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.facade;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;

import java.util.List;


public interface TravelClubFacade {
    //
    TravelClub findTravelClubById(String clubId);
    List<TravelClub> findTravelClubsByName(String name, boolean foundationTimeDescending);
    void modifyTravelClub(String clubId, NameValueList nameValueList);
    void removeTravelClub(String clubId);
}
