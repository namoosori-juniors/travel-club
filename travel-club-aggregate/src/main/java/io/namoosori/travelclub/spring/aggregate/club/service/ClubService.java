package io.namoosori.travelclub.spring.aggregate.club.service;

import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	List<TravelClub> findAllClubs();
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
