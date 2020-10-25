package io.namoosori.travelclub.spring.aggregate.sequence.maria;

import io.namoosori.travelclub.spring.aggregate.sequence.SequenceStore;
import io.namoosori.travelclub.spring.aggregate.sequence.maria.jpo.SequenceJpo;
import io.namoosori.travelclub.spring.aggregate.sequence.maria.repository.SequenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SequenceMariaStore implements SequenceStore {
    //
    private SequenceRepository sequenceRepository;

    public SequenceMariaStore(SequenceRepository sequenceRepository) {
        //
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public int increaseAndGet(String className) {
        //
        Optional<SequenceJpo> sequenceJpo = sequenceRepository.findById(className);
        int sequence = sequenceJpo.map(SequenceJpo::toSequence).orElse(0);

        sequenceRepository.save(new SequenceJpo(className, ++sequence));

        return sequence;
    }
}
