package io.namoosori.travelclub.spring.aggregate.membership.store.maria.repository;

import io.namoosori.travelclub.spring.aggregate.membership.store.maria.jpo.MembershipJpo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends CrudRepository<MembershipJpo, String> {
    //
    Optional<MembershipJpo> findByClubIdAndMemberId(String clubId, String memberId);
    List<MembershipJpo> findAllByClubId(String clubId, Sort sort);
    List<MembershipJpo> findAllByMemberId(String memberEmail, Sort sort);
}
