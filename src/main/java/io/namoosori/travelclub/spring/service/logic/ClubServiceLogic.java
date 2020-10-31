package io.namoosori.travelclub.spring.service.logic;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;

import java.util.List;

public class ClubServiceLogic implements ClubService {
	//
	private ClubStore clubStore;

	public ClubServiceLogic(ClubStore clubStore) {
		//
		this.clubStore = clubStore;
	}

	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		//
		TravelClub club = new TravelClub(clubCdo.getName(), clubCdo.getIntro());
		club.checkValidation();

		String clubId = clubStore.create(club);

		return clubId;
	}

	@Override
	public TravelClub findClubById(String id) {
		//
		return clubStore.retrieve(id);
	}

	@Override
	public List<TravelClub> findClubsByName(String name) {
		//
		return clubStore.retrieveByName(name);
	}

	@Override
	public void modify(String clubId, NameValueList nameValueList) {
		//
		TravelClub travelClub = clubStore.retrieve(clubId);

		if (travelClub == null) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}

		travelClub.modifyValues(nameValueList);

		clubStore.update(travelClub);
	}

	@Override
	public void remove(String clubId) {
		//
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}

		clubStore.delete(clubId);
	}
}
