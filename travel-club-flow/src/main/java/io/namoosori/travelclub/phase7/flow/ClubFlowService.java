package io.namoosori.travelclub.phase7.flow;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.aggregate.club.service.MembershipService;
import io.namoosori.travelclub.phase7.aggregate.member.service.MemberService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.RoleInClub;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.flow.ClubFlow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubFlowService implements ClubFlow {
    //
    private ClubService clubService;
    private MemberService memberService;
    private MembershipService membershipService;

    public ClubFlowService(ClubService clubService, MemberService memberService, MembershipService membershipService) {
        //
        this.clubService = clubService;
        this.memberService = memberService;
        this.membershipService = membershipService;
    }

    @Override
    public String registerTravelClub(TravelClubCdo travelClubCdo) {
        //
        String clubUsid = clubService.registerClub(travelClubCdo);
        String clubId = getClubId(clubUsid);

        String memberEmail = travelClubCdo.getPresidentEmail();
        String memberId = getMemberId(memberEmail);

        MembershipCdo membershipCdo = new MembershipCdo(clubId, memberId, RoleInClub.President);
        String membershipId = membershipService.registerMembership(membershipCdo);

        return membershipId;
    }

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        //
        String clubId = membershipCdo.getClubId();
        TravelClub travelClub = clubService.findClubById(clubId);

        String memberId = membershipCdo.getMemberId();
        CommunityMember member = memberService.findMemberById(memberId);

        String membershipId = membershipService.registerMembership(membershipCdo);

        return membershipId;
    }

    @Override
    public Membership findMembershipIn(String clubUsid, String memberEmail) {
        //
        String clubId = getClubId(clubUsid);
        String memberId = getMemberId(memberEmail);

        Membership membership = membershipService.findMembershipIn(clubId, memberId);

        return membership;
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubUsid) {
        //
        String clubId = getClubId(clubUsid);

        List<Membership> membershipList = membershipService.findAllMembershipsOfClub(clubId);

        return membershipList;
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberEmail) {
        //
        String memberId = getMemberId(memberEmail);

        List<Membership> membershipList = membershipService.findAllMembershipsOfMember(memberId);

        return membershipList;
    }


    private String getClubId(String clubUsid) {
        //
        TravelClub travelClub = clubService.findClubByUsid(clubUsid);
        String clubId = travelClub.getId();

        return clubId;
    }

    private String getMemberId(String memberEmail) {
        //
        CommunityMember member = memberService.findMemberByEmail(memberEmail);
        String memberId = member.getId();

        return memberId;
    }

}
