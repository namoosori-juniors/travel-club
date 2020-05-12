package io.namoosori.travelclub.phase7.spec.aggregate;

import java.io.Serializable;

public interface AutoIdEntity extends Serializable {
	//
	String getId();
	String getIdFormat();
	void setAutoId(String autoId);
}