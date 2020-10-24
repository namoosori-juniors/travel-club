package io.namoosori.travelclub.phase7.aggregate.sequence;

public interface IdSequenceStore {
    //
    String increaseAndGet(String key);
}
