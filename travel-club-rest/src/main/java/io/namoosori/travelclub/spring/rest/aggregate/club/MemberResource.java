package io.namoosori.travelclub.spring.rest.aggregate.club;

import io.namoosori.travelclub.spring.aggregate.member.service.MemberService;
import io.namoosori.travelclub.spring.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade.MemberFacade;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.MemberCdo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
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
        return memberService.registerMember(memberCdo);
    }

    @GetMapping("/{memberId}")
    @Override
    public CommunityMember findMemberById(@PathVariable String memberId) {
        //
        return memberService.findMemberById(memberId);
    }

    @GetMapping
    @Override
    public CommunityMember findMemberByEmail(@RequestParam String memberEmail) {
        //
        return memberService.findMemberByEmail(memberEmail);
    }

    @PutMapping("/{memberId}")
    @Override
    public void modifyMember(@PathVariable String memberId, @RequestBody NameValueList nameValueList) {
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
