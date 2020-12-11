package io.namoosori.travelclub.spring.aggregate.club.store.maria;

import io.namoosori.travelclub.spring.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.spring.aggregate.club.store.maria.jpo.TravelClubJpo;
import io.namoosori.travelclub.spring.aggregate.club.store.maria.repository.TravelClubRepository;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TravelClubMariaStore implements ClubStore {
    //
    private TravelClubRepository travelClubRepository;

    public TravelClubMariaStore(TravelClubRepository travelClubRepository) {
        //
        this.travelClubRepository = travelClubRepository;
    }

    @Override
    public String create(TravelClub club) {
        //
        TravelClubJpo travelClubJpo = travelClubRepository.save(new TravelClubJpo(club));
        return travelClubJpo.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        //
        Optional<TravelClubJpo> travelClubJpo = travelClubRepository.findById(clubId);
        return travelClubJpo.map(TravelClubJpo::toDomain).orElse(null);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        //
        Sort sort = Sort.by("foundationTime").descending();

        return travelClubRepository.findByNameContaining(name, sort).stream()
                .map(TravelClubJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<TravelClub> retrieveAll() {
        //
        List<TravelClub> travelClubs = new ArrayList<>();
        Iterable<TravelClubJpo> travelClubJpos = travelClubRepository.findAll();

        travelClubJpos.forEach(travelClubJpo -> travelClubs.add(travelClubJpo.toDomain()));

        return travelClubs;
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
