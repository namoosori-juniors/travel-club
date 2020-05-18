package io.namoosori.travelclub.phase7.spec.aggregate.club;

import io.namoosori.travelclub.phase7.spec.aggregate.Entity;
import io.namoosori.travelclub.phase7.spec.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Membership extends Entity {
	//
	private String clubId;
	private String memberId;
	private RoleInClub role;
	private String joinDate;

	public Membership(String id) {
		super(id);
	}

	public Membership(String clubId, String memberId) {
		//
		this.clubId = clubId; 
		this.memberId = memberId;
		
		this.role = RoleInClub.Member; 
		this.joinDate = DateUtil.today();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("club Id:").append(clubId);
		builder.append(", member Id:").append(memberId);
		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public static Membership getSample(TravelClub club, CommunityMember member) {
		// 
		Membership membership = new Membership(club.getId(), member.getEmail());
		membership.setRole(RoleInClub.Member);
		
		return membership; 
	}

	public static void main(String[] args) {
		// 
		System.out.println(Membership.getSample(TravelClub.getSample(), CommunityMember.getSample()));
	}
}