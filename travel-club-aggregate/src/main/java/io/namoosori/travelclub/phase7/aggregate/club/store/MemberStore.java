package io.namoosori.travelclub.phase7.aggregate.club.store;

import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;

import java.util.List;

public interface MemberStore {
	//
	String create(CommunityMember member);
	CommunityMember retrieve(String email);
	List<CommunityMember> retrieveByName(String name);
	void update(CommunityMember member);
	void delete(String email);
	
	boolean exists(String email);
}
