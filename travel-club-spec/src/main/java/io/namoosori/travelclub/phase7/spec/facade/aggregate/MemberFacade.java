package io.namoosori.travelclub.phase7.spec.facade.aggregate;

import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberCdo;

import java.util.List;

public interface MemberFacade {

//    String login(String string);
    String registerMember(MemberCdo memberCdo);
    CommunityMember findMemberById(String memberEmail);
    List<CommunityMember> findMembersByName(String name, boolean nameDescending);
    void modifyMember(String memberId, NameValueList nameValueList);
    void removeMember(String memberId);

}
