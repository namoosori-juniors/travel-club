package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.TravelClubFacade;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.flow.ClubFlow;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class TravelClubResource implements TravelClubFacade {
    //
    private ClubFlow clubFlowService;
    private ClubService clubService;

    public TravelClubResource(ClubFlow clubFlowService, ClubService clubService) {
        //
        this.clubFlowService = clubFlowService;
        this.clubService = clubService;
    }

//    @CacheEvict(key = "#travelClubCdo")
    @PostMapping
    @Override
    public String registerTravelClub(@RequestBody TravelClubCdo travelClubCdo) {
        //\
        String clubId = clubFlowService.registerTravelClub(travelClubCdo);

        return clubId;
    }

    @GetMapping("/{clubUsid}")
    @Override
    public TravelClub findTravelClubById(@PathVariable String clubUsid) {
        //
        TravelClub travelClub = clubService.findClubByUsid(clubUsid);
        return travelClub;
    }

//    @Cacheable(key = "#name", value = "clubsFound")
    @GetMapping
    @Override
    public List<TravelClub> findTravelClubsByName(@RequestParam(required = false) String name, @RequestParam(required = false, defaultValue = "true") boolean foundationTimeDescending) {
        //
        List<TravelClub> travelClubs = clubService.findClubsByName(name, foundationTimeDescending);
        return travelClubs;
    }

    @PutMapping("/{clubId}")
    @Override
    public void modifyTravelClub(@PathVariable String clubId, @RequestBody NameValueList nameValueList) {
        //
        clubService.modify(clubId, nameValueList);
    }

    @DeleteMapping("/{clubId}")
    @Override
    public void removeTravelClub(@PathVariable String clubId) {
        //
        clubService.remove(clubId);
    }

}
