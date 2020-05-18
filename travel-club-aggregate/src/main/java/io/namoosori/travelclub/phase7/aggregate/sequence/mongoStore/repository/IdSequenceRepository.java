package io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore.repository;

import io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore.jpo.IdSequenceJpo;
import org.springframework.data.repository.CrudRepository;

public interface IdSequenceRepository extends CrudRepository<IdSequenceJpo, String> {
    //
}
