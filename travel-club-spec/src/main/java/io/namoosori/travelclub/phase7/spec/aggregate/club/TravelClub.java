package io.namoosori.travelclub.phase7.spec.aggregate.club;

import io.namoosori.travelclub.phase7.spec.aggregate.AutoIdEntity;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValue;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TravelClub implements AutoIdEntity {
	//
	private static final int MINIMUM_NAME_LENGTH =  3;
	private static final int MINIMUM_INTRO_LENGTH =  10;
	public static final String ID_FORAMT = "%05d";

	private String usid; 		// auto incremental style
	private String name;
	private String intro;
	private long foundationTime;
	
	private String boardId;
	private List<ClubMembership> membershipList;
	
	private TravelClub() {
		this.membershipList = new ArrayList<>();
	}
	
	public TravelClub(String id, String name, String intro) {
		//
		this();
		this.usid = id;
		this.setName(name); 
		this.setIntro(intro); 
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
	
	public static TravelClub getSample(boolean keyIncluded) {
		// 
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";
		TravelClub club = new TravelClub("sample_id", name, intro);
		
		if (keyIncluded) {
			int sequence = 21; 
			club.setAutoId(String.format(ID_FORAMT, sequence));
		}

		return club; 
	}

	@Override
	public String getId() {
		return usid;
	}

	@Override
	public String getIdFormat() {
		return ID_FORAMT;
	}

	@Override
	public void setAutoId(String autoId) {
		//
		this.usid = autoId;
	}
	
	public ClubMembership getMembershipBy(String email) {
		//
		if (email == null || email.isEmpty()) {
			return null;
		}
		
		for (ClubMembership clubMembership : this.membershipList) {
			if(email.equals(clubMembership.getMemberEmail())){
				return clubMembership;
			}
		}
		return null;
	}

	public void setName(String name) {
		//
		if (name.length() < MINIMUM_NAME_LENGTH) {
			//
			throw new IllegalArgumentException("Name should be longer than " + MINIMUM_NAME_LENGTH);
		}

		this.name = name;
	}

	public void setIntro(String intro) {
		//
		if (intro.length() < MINIMUM_INTRO_LENGTH) {
			//
			throw new IllegalArgumentException("Intro should be longer than " + MINIMUM_INTRO_LENGTH);
		}

		this.intro = intro;
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
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