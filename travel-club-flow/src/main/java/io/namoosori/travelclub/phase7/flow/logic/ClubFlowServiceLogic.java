package io.namoosori.travelclub.phase7.flow.logic;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.aggregate.member.service.MemberService;
import io.namoosori.travelclub.phase7.aggregate.membership.service.MembershipService;
import io.namoosori.travelclub.phase7.flow.service.ClubFlowService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchClubException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

@Service
public class ClubFlowServiceLogic implements ClubFlowService {
    //
    private ClubService clubService;
    private MemberService memberService;
    private MembershipService membershipService;

    public ClubFlowServiceLogic(ClubService clubService,
                                MemberService memberService,
                                MembershipService membershipService) {
        //
        this.clubService = clubService;
        this.memberService = memberService;
        this.membershipService = membershipService;
    }

    @Override
    public String registerTravelClub(TravelClubCdo travelClubCdo) {
        //
        String memberEmail = travelClubCdo.getPresidentEmail();
        CommunityMember member = memberService.findMemberByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with email " + memberEmail);
        }

        travelClubCdo.checkValidation();
        String clubId = clubService.registerClub(travelClubCdo);

        MembershipCdo membershipCdo = new MembershipCdo(clubId, memberEmail, RoleInClub.President);
        String membershipId = membershipService.registerMembership(membershipCdo);

        return membershipId;
    }

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        //
        String clubId = membershipCdo.getClubId();
        TravelClub travelClub = clubService.findClubById(clubId);

        if (travelClub == null) {
            throw new NoSuchClubException("No such club with id " + clubId);
        }

        String memberId = membershipCdo.getMemberId();
        CommunityMember member = memberService.findMemberById(memberId);

        if (member == null) {
            throw new NoSuchMemberException("No such member with id " + memberId);
        }

        Membership membership = membershipService.findMembershipIn(clubId, memberId);

        if (membership == null) {
            throw new MembershipDuplicationException("Membership already exists.");
        }

        String membershipId = membershipService.registerMembership(membershipCdo);

        return membershipId;
    }
}
