package io.namoosori.travelclub.phase7.spec.aggregate;

import java.util.UUID;

public abstract class Entity {
	//
	protected String id;

	protected Entity() {
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}