package io.namoosori.travelclub.phase7.rest.flow;

import io.namoosori.travelclub.phase7.flow.service.ClubFlowService;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.MembershipCdo;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.facade.flow.club.facade.ClubFlowFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow")
public class ClubFlowResource implements ClubFlowFacade {
    //
    private ClubFlowService clubFlowService;

    public ClubFlowResource(ClubFlowService clubFlowService) {
        //
        this.clubFlowService = clubFlowService;
    }

    @PostMapping("/club")
    @Override
    public String registerTravelClub(@RequestBody TravelClubCdo travelClubCdo) {
        //
        return clubFlowService.registerTravelClub(travelClubCdo);
    }

    @PostMapping("/membership")
    @Override
    public String registerMembership(@RequestBody MembershipCdo membershipCdo) {
        //
        return clubFlowService.registerMembership(membershipCdo);
    }
}
