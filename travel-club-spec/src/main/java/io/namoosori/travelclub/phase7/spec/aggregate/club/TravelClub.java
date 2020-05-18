package io.namoosori.travelclub.phase7.spec.aggregate.club;

import io.namoosori.travelclub.phase7.spec.aggregate.Entity;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValue;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelClub extends Entity {
	//
	private static final int MINIMUM_NAME_LENGTH =  3;
	private static final int MINIMUM_INTRO_LENGTH =  10;

	private String usid; 		// auto incremental style
	private String name;
	private String intro;
	private long foundationTime;
	
	private String boardId;

	public TravelClub(String id) {
		//
		super(id);
	}

	public TravelClub(String usid, String name, String intro) {
		//
		super();
		this.usid = usid;
		this.name = name;
		this.intro = intro;
		this.foundationTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();
		
		builder.append("Travel Club Id:").append(usid); 
		builder.append(", name:").append(name); 
		builder.append(", intro:").append(intro); 
		builder.append(", foundation day:").append(foundationTime);
		
		return builder.toString(); 
	}
	
	public static TravelClub getSample() {
		// 
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";
		TravelClub club = new TravelClub("sample_id", name, intro);

		return club; 
	}

	public void validateName(String name) {
		//
		if (name.length() < MINIMUM_NAME_LENGTH) {
			//
			throw new IllegalArgumentException("Name should be longer than " + MINIMUM_NAME_LENGTH);
		}
	}

	public void validateIntro(String intro) {
		//
		if (intro.length() < MINIMUM_INTRO_LENGTH) {
			//
			throw new IllegalArgumentException("Intro should be longer than " + MINIMUM_INTRO_LENGTH);
		}
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValueList()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					this.name = value; break;
				case "intro":
					this.intro = value; break;
			}
		}
	}
}