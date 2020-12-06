package io.namoosori.travelclub.spring.aggregate.club.logic;

import io.namoosori.travelclub.spring.aggregate.club.service.ClubService;
import io.namoosori.travelclub.spring.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.spring.spec.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
	public List<TravelClub> findAllClubs() {
		//
		return clubStore.retrieveAll();
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
