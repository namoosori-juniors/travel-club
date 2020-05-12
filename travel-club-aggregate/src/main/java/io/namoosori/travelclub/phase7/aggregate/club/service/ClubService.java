package io.namoosori.travelclub.phase7.aggregate.club.service;

import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValue;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.ClubMembershipDto;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubDto;

import java.util.List;

public interface ClubService {
	//
	String registerClub(TravelClubCdo club);
	TravelClub findClub(String clubId);
	List<TravelClub> findClubsByName(String name, boolean descending);
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
	
	void addMembership(ClubMembershipDto membershipDto);
	ClubMembershipDto findMembershipIn(String clubId, String memberId);
	List<ClubMembershipDto> findAllMembershipsIn(String clubId);
	void modifyMembership(String clubId, ClubMembershipDto member);
	void removeMembership(String clubId, String memberId);
}