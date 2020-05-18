package io.namoosori.travelclub.phase7.aggregate.club.store.mongodbStore.repository;

import io.namoosori.travelclub.phase7.aggregate.club.store.mongodbStore.jpo.TravelClubJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TravelClubRepository extends CrudRepository<TravelClubJpo, String> {
    //
    Optional<TravelClubJpo> findByUsid(String usid);
    List<TravelClubJpo> findByNameContaining(String nameLike, Sort sort);
    List<TravelClubJpo> findAll(Sort sort);
}
