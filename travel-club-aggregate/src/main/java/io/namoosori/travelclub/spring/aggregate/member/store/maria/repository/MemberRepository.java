package io.namoosori.travelclub.spring.aggregate.member.store.maria.repository;

import io.namoosori.travelclub.spring.aggregate.member.store.maria.jpo.MemberJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<MemberJpo, String> {
    //
    Optional<MemberJpo> findByEmail(String memberEmail);
    List<MemberJpo> findByNameContaining(String nameLike, Sort sort);
}
