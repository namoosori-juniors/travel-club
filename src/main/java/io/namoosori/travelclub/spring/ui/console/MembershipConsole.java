package io.namoosori.travelclub.spring.ui.console;


import io.namoosori.travelclub.spring.aggregate.club.Membership;
import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.MembershipService;
import io.namoosori.travelclub.spring.service.sdo.MembershipCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.spring.util.exception.NoSuchClubException;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.spring.util.helper.ConsoleUtil;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.StringUtil;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MembershipConsole {
	//
	private TravelClub currentClub;

	@Autowired
	private MembershipService membershipService;
	@Autowired
	private ClubService clubService;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public MembershipConsole() {
		//
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public boolean hasCurrentClub() {
		//
		return Optional.ofNullable(currentClub).isPresent();
	}

	public String requestCurrentClubName() {
		//
		String clubName = null;

		if (hasCurrentClub()) {
			clubName = currentClub.getName();
		}

		return clubName;
	}

	public void findClub() {
		//
		TravelClub club = null;

		while (true) {
			String usid = consoleUtil.getValueOf("\n club usid to find(0.Membership menu) ");
			if (usid.equals("0")) {
				break;
			}

			club = clubService.findClubByUsid(usid);
			narrator.sayln("\t > Found club: " + club);
			break;
		}

		this.currentClub = club;
	}

	public void add() {
		//
		if (!hasCurrentClub()) {
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		while(true) {
			String email = consoleUtil.getValueOf("\n member's email to add(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			String memberRole = consoleUtil.getValueOf(" President|Member");

			try {
				MembershipCdo membershipCdo = new MembershipCdo(
						currentClub.getUsid(),
						email,
						RoleInClub.valueOf(memberRole)
				);

		        membershipService.registerMembership(membershipCdo);
				narrator.sayln(String.format("\t > Add a member[email:%s] in club[name:%s]", email, currentClub.getName()));

			} catch (NoSuchClubException | NoSuchMemberException | MembershipDuplicationException e) {
				narrator.sayln(e.getMessage());
			} catch (IllegalArgumentException e) {
				narrator.sayln("The Role in club should be president or member.");
			}
		}

	}

	public void find() {
		//
		if (!hasCurrentClub()) {
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		while (true) {
			String memberEmail = consoleUtil.getValueOf("\n email to find(0.Membership menu) ");
			if (memberEmail.equals("0")) {
				break;
			}

			try {
				Membership membership = membershipService.findMembershipByClubIdAndMemberEmail(currentClub.getUsid(), memberEmail);
				narrator.sayln("\t > Found membership information: " + membership);
			} catch (NoSuchMemberException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public Membership findOne() {
		//
		Membership membership = null;

		while (true) {
			String memberEmail = consoleUtil.getValueOf("\n member email to find(0.Membership menu) ");
			if (memberEmail.equals("0")) {
				break;
			}

			membership = membershipService.findMembershipByClubIdAndMemberEmail(currentClub.getUsid(), memberEmail);
			narrator.sayln("\t > Found membership information: " + membership);
			break;
		}

		return membership;
	}

	public void modify() {
		//
		if (!hasCurrentClub()) {
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		Membership membership = findOne();
		if (membership == null) {
			narrator.sayln("No such membership");
			return;
		}

		NameValueList nameValueList = new NameValueList();

		String newRole = consoleUtil.getValueOf("new President|Member(0.Membership menu, Enter. no change)");
		if (newRole.equals("0")) {
			return;
		}
		if (!StringUtil.isEmpty(newRole)) {
			nameValueList.addNameValue("role", newRole);
		}

		try {
			membershipService.modifyMembership(membership.getId(), nameValueList);

			Membership modifiedMembership = membershipService.findMembership(membership.getId());
			narrator.sayln("\t > Modified membership information: " + modifiedMembership);

		} catch (IllegalArgumentException e) {
			narrator.sayln("The Role in club should be president or member.");
		}
	}

	public void remove() {
		//
		if (!hasCurrentClub()) {
			narrator.sayln("No target club yet. Find target club first.");
			return;
		}

		Membership membership = findOne();
		if (membership == null) {
			narrator.sayln("No such membership");
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this member in the club? (Y:yes, N:no)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			//
			narrator.sayln("\t > Removing a membership -->" + membership.getMemberId());
			membershipService.removeMembership(membership.getClubId());
		} else {
			narrator.sayln("\t > Remove cancelled, membership is safe. --> " + membership.getMemberId());
		}
	}
}
