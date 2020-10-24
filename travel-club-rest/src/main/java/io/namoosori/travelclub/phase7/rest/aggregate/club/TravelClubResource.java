package io.namoosori.travelclub.phase7.rest.aggregate.club;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.facade.TravelClubFacade;
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

    @GetMapping("/{clubUsid}")
    @Override
    public TravelClub findTravelClubById(@PathVariable String clubUsid) {
        //
        return clubService.findClubByUsid(clubUsid);
    }

    @GetMapping
    @Override
    public List<TravelClub> findTravelClubsByName(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false, defaultValue = "true") boolean foundationTimeDescending) {
        //
        return clubService.findClubsByName(name, foundationTimeDescending);
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
