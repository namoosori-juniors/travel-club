package io.namoosori.travelclub.phase7.aggregate.member.store;

public interface IdSequenceStore {
    //
    String increaseAndGet(String key);
}
