package io.namoosori.travelclub.phase7.da.map.sequence.repository;

import io.namoosori.travelclub.phase7.da.map.sequence.jpo.IdSequenceJpo;
import org.springframework.data.repository.CrudRepository;

public interface IdSequenceRepository extends CrudRepository<IdSequenceJpo, String> {
    //
}
