package io.namoosori.travelclub.phase7.aggregate.club.service;

import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberDto;

import java.util.List;

public interface MemberService {
	//
	void register(MemberDto member);
	MemberDto find(String memberId);
	List<MemberDto> findByName(String memberName);
	void modify(MemberDto member);
	void remove(String memberId);
}