package io.namoosori.travelclub.spring.aggregate.club.logic;

import io.namoosori.travelclub.spring.aggregate.club.service.ClubService;
import io.namoosori.travelclub.spring.aggregate.club.store.ClubStore;
import io.namoosori.travelclub.spring.aggregate.sequence.SequenceStore;
import io.namoosori.travelclub.spring.spec.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.spec.facade.aggregate.club.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.spec.facade.shared.NameValueList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceLogic implements ClubService {
	//
	private ClubStore clubStore;
	private SequenceStore sequenceStore;

	public ClubServiceLogic(ClubStore clubStore, SequenceStore sequenceStore) {
		//
		this.clubStore = clubStore;
		this.sequenceStore = sequenceStore;
	}

	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		//
		clubCdo.checkValidation();

		String className = TravelClub.class.getSimpleName();
		int keySequence = sequenceStore.increaseAndGet(className);
		String sequence = String.format("%05d", keySequence);

		TravelClub club = new TravelClub(sequence, clubCdo.getName(), clubCdo.getIntro());
		String clubId = clubStore.create(club);

		return clubId;
	}

	@Override
	public TravelClub findClubById(String id) {
		//
		return clubStore.retrieve(id);
	}

	@Override
	public TravelClub findClubByUsid(String usid) {
		//
		return clubStore.retrieveByUsid(usid);
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
		travelClub.modifyValues(nameValueList);

		clubStore.update(travelClub);
	}

	@Override
	public void remove(String clubId) {
		//
		clubStore.delete(clubId);
	}
}
