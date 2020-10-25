package io.namoosori.travelclub.spring.store;

public interface SequenceStore {
    //
    int increaseAndGet(String key);
}
