package io.namoosori.travelclub.phase7.aggregate.sequence.mongo.repository;

import io.namoosori.travelclub.phase7.aggregate.sequence.mongo.jpo.IdSequenceJpo;
import org.springframework.data.repository.CrudRepository;

public interface IdSequenceRepository extends CrudRepository<IdSequenceJpo, String> {
    //
}
