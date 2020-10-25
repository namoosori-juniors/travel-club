package io.namoosori.travelclub.spring.aggregate.sequence;

public interface SequenceStore {
    //
    int increaseAndGet(String key);
}
