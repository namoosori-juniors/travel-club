package io.namoosori.travelclub.phase7.da.map.club;

import io.namoosori.travelclub.phase7.store.ClubStore;
import io.namoosori.travelclub.phase7.da.map.club.jpo.TravelClubJpo;
import io.namoosori.travelclub.phase7.da.map.club.repository.TravelClubRepository;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TravelClubMongoStore implements ClubStore {
    //
    private TravelClubRepository travelClubRepository;

    public TravelClubMongoStore(TravelClubRepository travelClubRepository) {
        //
        this.travelClubRepository = travelClubRepository;
    }

    @Override
    public String create(TravelClub club) {
        //
        TravelClubJpo travelClubJpo = travelClubRepository.save(new TravelClubJpo(club));
        return travelClubJpo.getUsid();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        //
        Optional<TravelClubJpo> travelClubJpo = travelClubRepository.findById(clubId);
        return travelClubJpo.map(TravelClubJpo::toDomain).orElse(null);
    }

    @Override
    public TravelClub retrieveByUsid(String clubUsid) {
        //
        Optional<TravelClubJpo> travelClubJpo = travelClubRepository.findByUsid(clubUsid);
        return travelClubJpo.map(TravelClubJpo::toDomain).orElse(null);
    }

    @Override
    public List<TravelClub> retrieveByName(String name, boolean foundationTimeDescending) {
        //
        Sort sort = Sort.by("foundationTime");
        sort = foundationTimeDescending ? sort.descending() : sort.ascending();

        if (name == null) {
            name = "*";
        }

        List<TravelClubJpo> travelClubJpos = travelClubRepository.findByNameContaining(name, sort);

        return travelClubJpos.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        //
        travelClubRepository.save(new TravelClubJpo(club));
    }

    @Override
    public void delete(String clubId) {
        //
        travelClubRepository.deleteById(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        //
        return travelClubRepository.existsById(clubId);
    }
}
