package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.TravelClubFacade;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class TravelClubResource implements TravelClubFacade {

    private ClubService clubService;

    public TravelClubResource(ClubService clubService) {
        //
        this.clubService = clubService;
    }

    @PostMapping
    @Override
    public String registerTravelClub(@RequestBody TravelClubCdo travelClubCdo) {
        //
        String clubId = clubService.registerClub(travelClubCdo);
        return clubId;
    }

    @Cacheable(key = "#clubId", value = "clubById")
    @GetMapping("/{clubId}")
    @Override
    public TravelClub findTravelClubById(@PathVariable String clubId) {
        //
        TravelClub club = clubService.findClub(clubId);
        return club;
    }

    @GetMapping
    @Override
    public List<TravelClub> findTravelClubsByName(@RequestParam(required = false) String clubName, @RequestParam(required = false, defaultValue = "true") boolean descending) {
        //
        return clubService.findClubsByName(clubName, descending);
    }

    @PutMapping("/{clubId}")
    @Override
    public void modifyTravelClub(@PathVariable String clubId, @RequestBody NameValueList nameValues) {
        //
        clubService.modify(clubId, nameValues);
    }
}
