package io.namoosori.travelclub.spring.rest.aggregate.club;

import io.namoosori.travelclub.spring.aggregate.membership.service.MembershipService;
import io.namoosori.travelclub.spring.spec.aggregate.club.Membership;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade.MembershipFacade;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
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

    @PostMapping
    @Override
    public String registerMembership(@RequestBody MembershipCdo membershipCdo) {
        //
        return membershipService.registerMembership(membershipCdo);
    }

    @GetMapping("/{membershipId}")
    @Override
    public Membership findMembership(@PathVariable String membershipId) {
        //
        return membershipService.findMembership(membershipId);
    }

    @GetMapping
    @Override
    public Membership findMembershipByClubIdAndMemberId(@RequestParam String clubId, @RequestParam String memberId) {
        //
        return membershipService.findMembershipByClubIdAndMemberId(clubId, memberId);
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
    public void modifyMembership(@RequestParam String membershipId, @RequestBody NameValueList nameValueList) {
        //
        membershipService.modifyMembership(membershipId, nameValueList);
    }

    @DeleteMapping("/{membershipId}")
    @Override
    public void removeMembership(@PathVariable String membershipId) {
        //
        membershipService.removeMembership(membershipId);
    }
}
