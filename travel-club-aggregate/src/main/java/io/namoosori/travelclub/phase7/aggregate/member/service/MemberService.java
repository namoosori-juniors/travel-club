package io.namoosori.travelclub.phase7.aggregate.member.service;

import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberCdo;

import java.util.List;

public interface MemberService {
	//
	String registerMember(MemberCdo member);
	CommunityMember findMemberById(String memberId);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name, boolean descending);
	void modifyMember(String memberEmail, NameValueList member);
	void removeMember(String memberId);
}