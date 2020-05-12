package io.namoosori.travelclub.phase7.aggregate.club.store.mongostore.repository;

import io.namoosori.travelclub.phase7.aggregate.club.store.mongostore.jpo.TravelClubJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TravelClubRepository extends PagingAndSortingRepository<TravelClubJpo, String> {
    //
    List<TravelClubJpo> findAllByNameContaining(String nameLike, Sort sort);
    List<TravelClubJpo> findAll(Sort sort);
}
