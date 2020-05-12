package io.namoosori.travelclub.phase7.aggregate.club.logic;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.phase7.aggregate.club.store.MemberStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.ClubMembership;
import io.namoosori.travelclub.phase7.spec.aggregate.club.CommunityMember;
import io.namoosori.travelclub.phase7.spec.aggregate.club.RoleInClub;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.ClubMembershipDto;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchClubException;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClubServiceLogic implements ClubService {
	//
	private ClubStore clubStore;
	private MemberStore memberStore;

	public ClubServiceLogic(ClubStore clubStore) {
		this.clubStore = clubStore;
	}

	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		//
//		Optional.ofNullable(clubStore.retrieveByName(clubCdo.getName())).ifPresent(dto->{throw new ClubDuplicationException("It is already exist the club name:" + clubCdo.getName());});

		String uuid = UUID.randomUUID().toString();
		TravelClub club = new TravelClub(uuid,clubCdo.getName(), clubCdo.getIntro());
		String clubId = clubStore.create(club);
		ClubMembership clubMembership = new ClubMembership(clubId, clubCdo.getPresidentEmail());
		clubMembership.setRole(RoleInClub.President);
		club.getMembershipList().add(clubMembership);
		clubStore.update(club);

		return clubId;
	}

	@Override
	public TravelClub findClub(String clubId) {
		//
		return Optional.ofNullable(clubStore.retrieve(clubId))
				.orElseThrow(()-> new NoSuchClubException("No such a club with id: " + clubId));
	}

	@Override
	public List<TravelClub> findClubsByName(String name, boolean descending) {
		//
		return clubStore.retrieveByName(name, descending);
	}

	@Override
	public void modify(String clubId, NameValueList nameValues) {
		//
		TravelClub travelClub = clubStore.retrieve(clubId);
		travelClub.modifyValues(nameValues);

		clubStore.update(travelClub);
	}

	@Override
	public void remove(String clubId) {
		//
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such a club with id: " + clubId);
		}

		clubStore.delete(clubId);
	}

	// Membership
	@Override
	public void addMembership(ClubMembershipDto membershipDto) {
		//
		// check existing member
		String memberId = membershipDto.getMemberEmail();

		CommunityMember member = Optional.ofNullable(memberStore.retrieve(memberId)).orElseThrow(()->new NoSuchClubException("No such a member with email: " + memberId));
		
		// check existing membership in the club
		TravelClub club = clubStore.retrieve(membershipDto.getClubId());
		for (ClubMembership membership : club.getMembershipList()) {
			if(memberId.equals(membership.getMemberEmail())) {
				throw new MemberDuplicationException("There is member in club already -->" + memberId);
			}
		}

		// add membership
		ClubMembership clubMembership = membershipDto.toMembership();
		club.getMembershipList().add(clubMembership);
		clubStore.update(club);
		member.getMembershipList().add(clubMembership);
		memberStore.update(member);
	}

	@Override
	public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
		//
		TravelClub club = clubStore.retrieve(clubId);

		ClubMembership membership = getMembershipIn(club, memberId);

		return new ClubMembershipDto(membership);
	}

	@Override
	public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
		//
		TravelClub club = clubStore.retrieve(clubId);

		return club.getMembershipList()
				.stream()
				.map(membership->new ClubMembershipDto(membership))
				.collect(Collectors.toList());
	}

	@Override
	public void modifyMembership(String clubId, ClubMembershipDto newMembership) {
		//
		String targetEmail = newMembership.getMemberEmail();
		RoleInClub newRole = newMembership.getRole();

		// modify membership of the club.
		TravelClub targetClub = clubStore.retrieve(clubId);
		ClubMembership membershipOfClub = getMembershipIn(targetClub, targetEmail);
		membershipOfClub.setRole(newRole);
		clubStore.update(targetClub);

		// modify membership of the member.
		CommunityMember targetMember = memberStore.retrieve(targetEmail);
		targetMember.getMembershipList().stream().forEach(membershipOfMember->{
			if(membershipOfMember.getClubId().equals(clubId)) membershipOfMember.setRole(newRole);
		});
		memberStore.update(targetMember);

	}

	@Override
	public void removeMembership(String clubId, String memberId) {
		//
		TravelClub foundClub = clubStore.retrieve(clubId);
		CommunityMember foundMember = memberStore.retrieve(memberId);
		ClubMembership clubMembership = getMembershipIn(foundClub, memberId);

		// remove membership
		foundClub.getMembershipList().remove(clubMembership);
		clubStore.update(foundClub);
		foundMember.getMembershipList().remove(clubMembership);
		memberStore.update(foundMember);

	}

	private ClubMembership getMembershipIn(TravelClub club, String memberEmail) {
		//
		for (ClubMembership membership : club.getMembershipList()) {
			if(memberEmail.equals(membership.getMemberEmail())) {
				return membership;
			}
		}

		String message = String.format("No such a member[email:%s] in club[name:%s]", memberEmail, club.getName());
		throw new NoSuchMemberException(message);
	}
}
