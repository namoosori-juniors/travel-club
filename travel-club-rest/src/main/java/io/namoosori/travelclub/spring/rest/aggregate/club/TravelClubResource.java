package io.namoosori.travelclub.spring.rest.aggregate.club;

import io.namoosori.travelclub.spring.aggregate.club.service.ClubService;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.facade.TravelClubFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class TravelClubResource implements TravelClubFacade {
    //
    private ClubService clubService;

    public TravelClubResource(ClubService clubService) {
        //
        this.clubService = clubService;
    }

    @PostMapping
    @Override
    public String registerClub(@RequestBody TravelClubCdo travelClubCdo) {
        //
        return clubService.registerClub(travelClubCdo);
    }

    @GetMapping("/{clubId}")
    @Override
    public TravelClub findTravelClubById(@PathVariable String clubId) {
        //
        return clubService.findClubById(clubId);
    }

    @GetMapping
    @Override
    public List<TravelClub> findTravelClubsByName(@RequestParam String name) {
        //
        return clubService.findClubsByName(name);
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
