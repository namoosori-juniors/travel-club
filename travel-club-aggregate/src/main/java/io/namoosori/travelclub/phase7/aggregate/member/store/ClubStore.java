package io.namoosori.travelclub.phase7.aggregate.member.store;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;

import java.util.List;

public interface ClubStore {
	//
	String create(TravelClub club);
	TravelClub retrieveByUsid(String clubUsid);
	List<TravelClub> retrieveByName(String name, boolean foundationTimeDescending);
	List<TravelClub> retrieveSortBy(String propertyName, boolean descending);
	void update(TravelClub club);
	void delete(String clubId);
	
	boolean exists(String clubId);
}