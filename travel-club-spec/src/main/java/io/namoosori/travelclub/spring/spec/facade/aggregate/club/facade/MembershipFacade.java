package io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade;

import io.namoosori.travelclub.spring.spec.aggregate.club.Membership;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;

import java.util.List;

public interface MembershipFacade {
    //
    String registerMembership(MembershipCdo membershipCdo);
    Membership findMembership(String membershipId);
    Membership findMembershipByClubIdAndMemberId(String clubId, String memberId);
    List<Membership> findAllMembershipsOfClub(String clubId);
    List<Membership> findAllMembershipsOfMember(String memberId);
    void modifyMembership(String membershipId, NameValueList nameValueList);
    void removeMembership(String membershipId);
}
