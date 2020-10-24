package io.namoosori.travelclub.phase7.facade;

import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.service.sdo.MemberCdo;
import io.namoosori.travelclub.phase7.shared.NameValueList;

import java.util.List;

public interface MemberFacade {
    //
    String registerMember(MemberCdo memberCdo);
    CommunityMember findMemberById(String memberEmail);
    List<CommunityMember> findMembersByName(String name, boolean descending);
    void modifyMember(String memberId, NameValueList nameValueList);
    void removeMember(String memberId);
}
