package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo;

import io.namoosori.travelclub.phase7.spec.aggregate.club.ClubMembership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.util.helper.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TravelClubDto {
	//
	private String usid;
	private String name;
	private String intro;
	private long foundationTime;
	
	private List<ClubMembershipDto> membershipList;
	
	private TravelClubDto() {
		//
		this.membershipList = new ArrayList<>();
	}
	
	public TravelClubDto(String name, String intro) {
		//
		this();
		this.name = name;
		this.intro = intro;
		this.foundationTime = System.currentTimeMillis();
	}
	
	public TravelClubDto(TravelClub club) {
		//
		this();
		usid = club.getUsid();
		name = club.getName();
		intro = club.getIntro();
		foundationTime = club.getFoundationTime();
		
		for (ClubMembership membership : club.getMembershipList()) {
			//
			membershipList.add(new ClubMembershipDto(membership));
		}
	}

	public TravelClub toTravelClub() {
		//
		TravelClub travelClub = new TravelClub(usid, name, intro);
		travelClub.setFoundationTime(foundationTime);
		
		for (ClubMembershipDto membershipDto : membershipList) {
			//
			travelClub.getMembershipList().add(membershipDto.toMembership());
		} 
		return travelClub;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("Travel Club Id:").append(usid);
		builder.append(", name:").append(name);
		builder.append(", intro:").append(intro);
		builder.append(", foundation day:").append(foundationTime);
		int i=0; 
		for(ClubMembershipDto membership : membershipList) {
			builder.append(" ["+ i +"] Club member ").append(membership.toString()).append("\n");
			i++;
		}
		
		return builder.toString(); 
	}
	
	public String getUsid() {
		return usid; 
	}

	public void setUsid(String usid) {
		//
		this.usid = usid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<ClubMembershipDto> getMembershipList() {
		return membershipList;
	}
}
