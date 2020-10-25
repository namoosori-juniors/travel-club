package io.namoosori.travelclub.spring.ui.console;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.util.helper.ConsoleUtil;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;
import org.springframework.beans.factory.annotation.Autowired;

public class ClubConsole {
	//
	@Autowired
	private ClubService clubService;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public ClubConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		while (true) {
			//
			String name = consoleUtil.getValueOf("\n club name(0.Club menu)");
			if (name.equals("0")) {
				return;
			}
			String intro = consoleUtil.getValueOf(" club intro(0.Club menu)");
			if (intro.equals("0")) {
				return;
			}

			try {
				TravelClubCdo travelClubCdo = new TravelClubCdo(name, intro);
				String travelClubId = clubService.registerClub(travelClubCdo);
				TravelClub travelClub = clubService.findClubById(travelClubId);
				narrator.say("\t > Registered club :" + travelClub.toString());
			} catch (IllegalArgumentException e) {
				//
				narrator.sayln(e.getMessage());
			}
		}
	}

	public void find() {
		//
		while (true) {
			//
			String name = consoleUtil.getValueOf("\n club name to find(0.Club menu) ");
			if (name.equals("0")) {
				break;
			}

			narrator.sayln("==== Found Club List ====");
			clubService.findClubsByName(name)
					.forEach(club -> narrator.sayln(club.toString()));
		}
	}

	public TravelClub findOne() {
		//
		TravelClub club = null;

		while (true) {
			//
			String usid = consoleUtil.getValueOf("\n Club usid to find(0.Club menu) ");
			if (usid.equals("0")) {
				break;
			}

			club = clubService.findClubByUsid(usid);
			narrator.sayln("\t > Found club: " + club);
			break;
		}

		return club;
	}

	public void modify() {
		//
		TravelClub club = findOne();
		if (club == null) {
			narrator.sayln("No such club");
			return;
		}

		NameValueList nameValueList = new NameValueList();

		String newName = consoleUtil.getValueOf("\n new club name(0.Club menu, Enter. no change)");
		if (newName.equals("0")) {
			return;
		}
		nameValueList.addNameValue("name", newName);

		String newIntro = consoleUtil.getValueOf(" new club intro(Enter. no change)");
		nameValueList.addNameValue("intro", newIntro);

		try {
			clubService.modify(club.getId(), nameValueList);
			narrator.sayln("\t > Modified club: " + club);
		} catch (IllegalArgumentException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void remove() {
		//
		TravelClub club = findOne();
		if (club == null) {
			narrator.sayln("No such club");
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this club? (Y:yes, N:no)");

		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			narrator.sayln("\t > Removing a club --> " + club.getName());
			clubService.remove(club.getUsid());
		} else {
			narrator.sayln("\t > Remove cancelled, your club is safe. --> " + club.getName());
		}
	}
}