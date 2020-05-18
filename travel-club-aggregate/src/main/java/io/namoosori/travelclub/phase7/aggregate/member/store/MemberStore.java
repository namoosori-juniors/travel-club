package io.namoosori.travelclub.phase7.aggregate.member.store;

import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;

import java.util.List;

public interface MemberStore {
	//
	String create(CommunityMember member);
	CommunityMember retrieve(String memberId);
	CommunityMember retrieveByEmail(String email);
	List<CommunityMember> retrieveByName(String name, boolean descending);
	void update(CommunityMember member);
	void delete(String email);
	
	boolean exists(String memberId);
}
