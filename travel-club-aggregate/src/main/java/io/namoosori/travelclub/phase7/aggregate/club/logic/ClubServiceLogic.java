package io.namoosori.travelclub.phase7.aggregate.club.logic;

import io.namoosori.travelclub.phase7.aggregate.club.service.ClubService;
import io.namoosori.travelclub.phase7.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.phase7.aggregate.sequence.IdSequenceStore;
import io.namoosori.travelclub.phase7.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.phase7.spec.facade.shared.NameValueList;
import io.namoosori.travelclub.phase7.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.phase7.spec.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceLogic implements ClubService {
	//
	private ClubStore clubStore;
	private IdSequenceStore idSequenceStore;

	public ClubServiceLogic(ClubStore clubStore, IdSequenceStore idSequenceStore) {
		//
		this.clubStore = clubStore;
		this.idSequenceStore = idSequenceStore;
	}

	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		//
		String sequence = idSequenceStore.increaseAndGet(TravelClub.class.getSimpleName());

		TravelClub club = new TravelClub(sequence, clubCdo.getName(), clubCdo.getIntro());
		String clubId = clubStore.create(club);

		return clubId;
	}

	@Override
	public TravelClub findClubById(String clubId) {
		//
		return clubStore.retrieve(clubId);
	}

	@Override
	public TravelClub findClubByUsid(String clubUsid) {
		//
		return clubStore.retrieveByUsid(clubUsid);
	}

	@Override
	public List<TravelClub> findClubsByName(String name, boolean foundationTimeDescending) {
		//
		return clubStore.retrieveByName(name, foundationTimeDescending);
	}

	@Override
	public void modify(String clubId, NameValueList nameValueList) {
		//
		TravelClub travelClub = clubStore.retrieve(clubId);
		travelClub.modifyValues(nameValueList);

		clubStore.update(travelClub);
	}

	@Override
	public void remove(String clubId) {
		//
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such club with id: " + clubId);
		}

		clubStore.delete(clubId);
	}
}
