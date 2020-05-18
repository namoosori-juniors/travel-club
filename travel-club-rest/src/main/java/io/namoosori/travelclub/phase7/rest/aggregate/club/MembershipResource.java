package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.club.service.MembershipService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.MembershipFacade;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.flow.ClubFlow;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipResource implements MembershipFacade {
    //
    private ClubFlow clubFlowService;
    private MembershipService membershipService;

    public MembershipResource(ClubFlow clubFlowService, MembershipService membershipService) {
        //
        this.clubFlowService = clubFlowService;
        this.membershipService = membershipService;
    }

    @PostMapping
    @Override
    public String registerMembership(@RequestBody MembershipCdo membershipCdo) {
        //
        String membershipId = clubFlowService.registerMembership(membershipCdo);
        return membershipId;
    }

    @GetMapping
    @Override
    public Membership findMembershipIn(@RequestParam String clubId, @RequestParam String memberId) {
        //
        Membership membership = clubFlowService.findMembershipIn(clubId, memberId);
        return membership;
    }

    @GetMapping("/club")
    @Override
    public List<Membership> findAllMembershipsOfClub(@RequestParam String clubId) {
        //
        List<Membership> membershipList = clubFlowService.findAllMembershipsOfClub(clubId);
        return membershipList;
    }

    @GetMapping("/member")
    @Override
    public List<Membership> findAllMembershipsOfMember(@RequestParam String memberId) {
        //
        List<Membership> membershipList = clubFlowService.findAllMembershipsOfMember(memberId);
        return membershipList;
    }

    @PutMapping
    @Override
    public void modifyMembership(@RequestBody MembershipCdo membershipCdo) {
        //
        membershipService.modifyMembership(membershipCdo);
    }

    @DeleteMapping
    @Override
    public void removeMembership(@RequestParam String clubId, @RequestParam String memberId) {
        //
        membershipService.removeMembership(clubId, memberId);
    }
}
