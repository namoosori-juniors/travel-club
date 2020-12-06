package io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade;

import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MemberCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;

import java.util.List;

public interface MemberFacade {
    //
    String registerMember(MemberCdo memberCdo);
    CommunityMember findMemberById(String memberId);
    List<CommunityMember> findMemberByName(String name);
    List<CommunityMember> findAllMembers();
    void modifyMember(String memberId, NameValueList nameValueList);
    void removeMember(String memberId);
}
