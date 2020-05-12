package io.namoosori.travelclub.phase7.aggregate.club.store.mongostore;

import io.namoosori.travelclub.phase7.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.phase7.aggregate.club.store.mongostore.jpo.TravelClubJpo;
import io.namoosori.travelclub.phase7.aggregate.club.store.mongostore.repository.TravelClubRepository;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClubMongoStore implements ClubStore {

    private TravelClubRepository travelClubRepository;

    public ClubMongoStore(TravelClubRepository travelClubRepository) {
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
    public List<TravelClub> retrieveByName(String nameLike, boolean foundationTimeDescending) {
        //
        Sort sort = Sort.by("foundationTime");
        sort = foundationTimeDescending ? sort.descending() : sort.ascending();

        if (nameLike == null) {
            nameLike = "";
        }

        return travelClubRepository.findAllByNameContaining(nameLike, sort)
                .stream().map(TravelClubJpo::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        //
        travelClubRepository.save(new TravelClubJpo(club));
    }

    @Override
    public void delete(String clubId) {
        //

    }

    @Override
    public boolean exists(String clubId) {
        return false;
    }
}
