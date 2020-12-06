package io.namoosori.travelclub.spring.aggregate.member.service;

import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MemberCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;

import java.util.List;

public interface MemberService {
	//
	String registerMember(MemberCdo member);
	CommunityMember findMemberById(String memberId);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	List<CommunityMember> findAllMembers();
	void modifyMember(String memberId, NameValueList member);
	void removeMember(String memberId);
}
