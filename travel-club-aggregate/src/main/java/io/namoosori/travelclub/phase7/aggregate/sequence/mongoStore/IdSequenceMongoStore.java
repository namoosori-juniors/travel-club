package io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore;

import io.namoosori.travelclub.phase7.aggregate.member.store.IdSequenceStore;
import io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore.jpo.IdSequenceJpo;
import io.namoosori.travelclub.phase7.aggregate.sequence.mongoStore.repository.IdSequenceRepository;
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
        System.out.println(sequence);

        return String.valueOf(sequence);
    }
}
