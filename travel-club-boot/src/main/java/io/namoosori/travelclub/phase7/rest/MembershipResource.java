package io.namoosori.travelclub.phase7.rest;

import io.namoosori.travelclub.phase7.aggregate.membership.service.MembershipService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.Membership;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.facade.MembershipFacade;
import io.namoosori.travelclub.phase7.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipResource implements MembershipFacade {
    //
    private MembershipService membershipService;

    public MembershipResource(MembershipService membershipService) {
        //
        this.membershipService = membershipService;
    }

    @GetMapping
    @Override
    public Membership findMembershipIn(@RequestParam String clubId, @RequestParam String memberId) {
        //
        return membershipService.findMembershipIn(clubId, memberId);
    }

    @GetMapping("/club")
    @Override
    public List<Membership> findAllMembershipsOfClub(@RequestParam String clubId) {
        //
        return membershipService.findAllMembershipsOfClub(clubId);
    }

    @GetMapping("/member")
    @Override
    public List<Membership> findAllMembershipsOfMember(@RequestParam String memberId) {
        //
        return membershipService.findAllMembershipsOfMember(memberId);
    }

    @PutMapping
    @Override
    public void modifyMembership(@RequestParam String clubId, @RequestParam String memberId, @RequestBody NameValueList nameValueList) {
        //
        membershipService.modifyMembership(clubId, memberId, nameValueList);
    }

    @DeleteMapping
    @Override
    public void removeMembership(@RequestParam String clubId, @RequestParam String memberId) {
        //
        membershipService.removeMembership(clubId, memberId);
    }
}
