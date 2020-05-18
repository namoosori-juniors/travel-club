package io.namoosori.travelclub.phase7.aggregate.club.store;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;

import java.util.List;

public interface ClubStore {
	//
	String create(TravelClub club);
	TravelClub retrieve(String clubId);
	TravelClub retrieveByUsid(String clubUsid);
	List<TravelClub> retrieveByName(String name, boolean foundationTimeDescending);
	void update(TravelClub club);
	void delete(String clubId);
	
	boolean exists(String clubId);
}