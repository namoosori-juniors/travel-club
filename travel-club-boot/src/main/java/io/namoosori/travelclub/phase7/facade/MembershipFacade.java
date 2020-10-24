package io.namoosori.travelclub.phase7.facade;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.shared.NameValueList;

import java.util.List;

public interface MembershipFacade {
    //
    Membership findMembershipIn(String clubId, String memberId);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    void modifyMembership(String clubId, String memberId, NameValueList nameValueList);
    void removeMembership(String clubId, String memberId);
}
