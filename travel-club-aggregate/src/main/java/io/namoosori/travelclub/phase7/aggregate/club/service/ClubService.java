package io.namoosori.travelclub.phase7.aggregate.club.service;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String clubId);
	TravelClub findClubByUsid(String clubUsid);
	List<TravelClub> findClubsByName(String name, boolean descending);
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}