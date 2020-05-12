package io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo;


import io.namoosori.travelclub.phase7.spec.aggregate.club.ClubMembership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.RoleInClub;
import io.namoosori.travelclub.phase7.spec.util.helper.DateUtil;

public class ClubMembershipDto {
	//
	private String clubId;
	private String memberEmail;
	private RoleInClub role;
	private String joinDate;

	private ClubMembershipDto() {
		//
		this.role = RoleInClub.Member;
		this.joinDate = DateUtil.today();
	}

	public ClubMembershipDto(String clubId, String memberEmail) {
		//
		this();
		this.clubId = clubId;
		this.memberEmail = memberEmail;
	}

	public ClubMembershipDto(TravelClubDto clubDto, MemberDto memberDto) {
		//
		this();
		this.clubId = clubDto.getUsid();
		this.memberEmail = memberDto.getEmail();
	}

	public ClubMembershipDto(ClubMembership membership) {
		//
		this.clubId = membership.getClubId();
		this.memberEmail = membership.getMemberEmail();
		this.role = membership.getRole();
		this.joinDate = membership.getJoinDate();
	}

	public ClubMembership toMembership() {
		//
		ClubMembership membership = new ClubMembership(clubId, memberEmail);
		membership.setRole(role);
		membership.setJoinDate(joinDate);
		return membership;
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder();

		builder.append("club Id:").append(clubId);
		builder.append(", member email:").append(memberEmail);
		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public RoleInClub getRole() {
		return role;
	}

	public void setRole(RoleInClub role) {
		this.role = role;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
}
