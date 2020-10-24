package io.namoosori.travelclub.phase7.service;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.service.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.shared.NameValueList;

import java.util.List;

public interface MembershipService {
    //
    String registerMembership(MembershipCdo membershipCdo);
    Membership findMembershipIn(String clubId, String memberId);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    void modifyMembership(String clubId, String memberId, NameValueList nameValueList);
    void removeMembership(String clubId, String memberId);
}
