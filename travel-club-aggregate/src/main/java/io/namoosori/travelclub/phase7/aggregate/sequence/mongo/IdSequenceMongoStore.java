package io.namoosori.travelclub.phase7.aggregate.sequence.mongo;

import io.namoosori.travelclub.phase7.aggregate.sequence.IdSequenceStore;
import io.namoosori.travelclub.phase7.aggregate.sequence.mongo.jpo.IdSequenceJpo;
import io.namoosori.travelclub.phase7.aggregate.sequence.mongo.repository.IdSequenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IdSequenceMongoStore implements IdSequenceStore {
    //
    private IdSequenceRepository idSequenceRepository;

    public IdSequenceMongoStore(IdSequenceRepository idSequenceRepository) {
        //
        this.idSequenceRepository = idSequenceRepository;
    }

    @Override
    public String increaseAndGet(String className) {
        //
        Optional<IdSequenceJpo> sequenceFromRepository = idSequenceRepository.findById(className);
        int sequence = sequenceFromRepository.map(IdSequenceJpo::toSequence).orElse(0);

        idSequenceRepository.save(new IdSequenceJpo(className, ++sequence));

        return String.valueOf(sequence);
    }
}
