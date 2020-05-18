package io.namoosori.travelclub.phase7.aggregate.member.store.mongoStore.repository;

import io.namoosori.travelclub.phase7.aggregate.member.store.mongoStore.jpo.MemberJpo;
//import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<MemberJpo, String> {
    //
//    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter}")
//    List<MemberJpo> findAll();
    Optional<MemberJpo> findByEmail(String memberEmail);
    List<MemberJpo> findByNameContaining(String nameLike, Sort sort);
}
