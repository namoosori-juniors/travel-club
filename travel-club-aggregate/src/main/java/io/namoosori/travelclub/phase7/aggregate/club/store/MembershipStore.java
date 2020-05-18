package io.namoosori.travelclub.phase7.aggregate.club.store;

import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;

import java.util.List;

public interface MembershipStore {
    //
    String create(Membership membership);
    Membership retrieve(String clubId, String memberEmail);
    List<Membership> retrieveByClubId(String clubId, boolean descending);
    List<Membership> retrieveByMemberId(String memberId, boolean descending);
    void update(Membership membership);
    void delete(String clubId, String memberEmail);

    boolean exists(String clubId, String memberEmail);
}
