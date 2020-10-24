package io.namoosori.travelclub.phase7.da.map.sequence;

import io.namoosori.travelclub.phase7.store.IdSequenceStore;
import io.namoosori.travelclub.phase7.da.map.sequence.jpo.IdSequenceJpo;
import io.namoosori.travelclub.phase7.da.map.sequence.repository.IdSequenceRepository;
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
