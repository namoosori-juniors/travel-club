package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.member.service.MemberService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.facade.MemberFacade;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MemberCdo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{memberEmail}")
    @Override
    public CommunityMember findMemberById(@PathVariable String memberEmail) {
        //
        return memberService.findMemberByEmail(memberEmail);
    }

    @GetMapping
    @Override
    public List<CommunityMember> findMembersByName(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false, defaultValue = "false") boolean descending) {
        //
        return memberService.findMembersByName(name, descending);
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
