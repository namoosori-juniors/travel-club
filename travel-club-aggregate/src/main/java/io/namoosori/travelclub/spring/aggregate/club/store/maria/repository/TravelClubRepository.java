package io.namoosori.travelclub.spring.aggregate.club.store.maria.repository;

import io.namoosori.travelclub.spring.aggregate.club.store.maria.jpo.TravelClubJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TravelClubRepository extends CrudRepository<TravelClubJpo, String> {
    //
    List<TravelClubJpo> findByNameContaining(String nameLike, Sort sort);
}
