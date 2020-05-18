package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.member.service.MemberService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.MemberFacade;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberCdo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberResource implements MemberFacade {

    private MemberService memberService;

    public MemberResource(MemberService memberService) {
        //
        this.memberService = memberService;
    }

    @PostMapping
    @Override
    public String registerMember(@RequestBody MemberCdo memberCdo) {
        //
        String memberId = memberService.registerMember(memberCdo);
        return memberId;
    }

    @GetMapping("/{memberEmail}")
    @Override
    public CommunityMember findMemberById(@PathVariable String memberEmail) {
        //
        CommunityMember member = memberService.findMemberByEmail(memberEmail);
        return member;
    }

    @GetMapping
    @Override
    public List<CommunityMember> findMembersByName(@RequestParam(required = false) String name, @RequestParam(required = false, defaultValue = "true") boolean nameDescending) {
        //
        List<CommunityMember> members = memberService.findMembersByName(name, nameDescending);
        return members;
    }

    @PutMapping("/{memberId}")
    @Override
    public void modifyMember(@PathVariable String memberId,@RequestBody NameValueList nameValueList) {
        //
        memberService.modifyMember(memberId, nameValueList);
    }

    @DeleteMapping("/{memberId}")
    @Override
    public void removeMember(@PathVariable String memberId) {
        //
        memberService.removeMember(memberId);

    }
}
