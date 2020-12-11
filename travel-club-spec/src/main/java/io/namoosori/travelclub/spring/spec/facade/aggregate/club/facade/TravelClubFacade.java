package io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade;

import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;

import java.util.List;


public interface TravelClubFacade {
    //
    String registerClub(TravelClubCdo travelClubCdo);
    TravelClub findTravelClubById(String clubId);
    List<TravelClub> findTravelClubsByName(String name);
    List<TravelClub> findAllTravelClubs();
    void modifyTravelClub(String clubId, NameValueList nameValueList);
    void removeTravelClub(String clubId);
}
