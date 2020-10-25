package io.namoosori.travelclub.spring.da.map;

import io.namoosori.travelclub.spring.aggregate.club.Membership;
import io.namoosori.travelclub.spring.store.MembershipStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MembershipMapStore implements MembershipStore {
	//
	private Map<String, Membership> membershipMap;

	public MembershipMapStore() {
		//
		this.membershipMap = new LinkedHashMap<>();
	}

	@Override
	public String create(Membership membership) {
		//
		membershipMap.put(membership.getId(), membership);
		return membership.getId();
	}

	@Override
	public Membership retrieve(String clubId, String memberId) {
		//
		Membership targetMembership = null;

		for (Membership membership : membershipMap.values()) {
			if (membership.getClubId().equals(clubId) && membership.getMemberId().equals(memberId)) {
				targetMembership = membership;
				break;
			}
		}

		return targetMembership;
	}

	@Override
	public List<Membership> retrieveByClubId(String clubId) {
		//
		return membershipMap.values().stream()
				.filter(membership -> membership.getClubId().equals(clubId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Membership> retrieveByMemberId(String memberId) {
		//
		return membershipMap.values().stream()
				.filter(membership -> membership.getMemberId().equals(memberId))
				.collect(Collectors.toList());
	}

	@Override
	public void update(Membership membership) {
		//
		membershipMap.put(membership.getId(), membership);
	}

	@Override
	public void delete(String clubId, String memberId) {
		//
		Membership targetMembership = retrieve(clubId, memberId);
		membershipMap.remove(targetMembership.getId());
	}

	@Override
	public boolean exists(String clubId, String memberId) {
		//
		return Optional.ofNullable(retrieve(clubId, memberId)).isPresent();
	}
}
