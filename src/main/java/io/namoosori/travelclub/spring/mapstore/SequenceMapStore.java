package io.namoosori.travelclub.spring.mapstore;

import io.namoosori.travelclub.spring.store.SequenceStore;

import java.util.LinkedHashMap;
import java.util.Map;

public class SequenceMapStore implements SequenceStore {
	//
	private Map<String, Integer> sequenceMap;

	public SequenceMapStore() {
		//
		this.sequenceMap = new LinkedHashMap<>();
	}

	@Override
	public int increaseAndGet(String key) {
		//
		if (sequenceMap.get(key) == null) {
			sequenceMap.put(key, 1);
		}

		int keySequence = sequenceMap.get(key);
		sequenceMap.put(key, keySequence + 1);

		return keySequence;
	}
}
