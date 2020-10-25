package io.namoosori.travelclub.spring.aggregate.sequence.maria.repository;

import io.namoosori.travelclub.spring.aggregate.sequence.maria.jpo.SequenceJpo;
import org.springframework.data.repository.CrudRepository;

public interface SequenceRepository extends CrudRepository<SequenceJpo, String> {
    //
}
