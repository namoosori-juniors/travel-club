package io.namoosori.travelclub.phase7.spec.aggregate.club;

import io.namoosori.travelclub.phase7.spec.aggregate.Entity;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValue;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelClub extends Entity {
	//
	public static final int MINIMUM_NAME_LENGTH =  3;
	public static final int MINIMUM_INTRO_LENGTH =  10;

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
	
	public static TravelClub sample() {
		// 
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";

		return new TravelClub("00001", name, intro);
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					this.name = value;
					break;
				case "intro":
					this.intro = value;
					break;
			}
		}
	}

	public static void main(String[] args) {
		//
		System.out.println(sample().toString());
	}
}