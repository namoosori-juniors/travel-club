package io.namoosori.travelclub.spring.ui.console;

import io.namoosori.travelclub.spring.aggregate.club.CommunityMember;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.sdo.MemberCdo;
import io.namoosori.travelclub.spring.shared.NameValueList;
import io.namoosori.travelclub.spring.util.exception.InvalidEmailException;
import io.namoosori.travelclub.spring.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.spring.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.spring.util.helper.ConsoleUtil;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;

public class MemberConsole {
	//
	private MemberService memberService;

	private ConsoleUtil consoleUtil;
	private Narrator narrator;

	public MemberConsole(MemberService memberService) {
		//
		this.memberService = memberService;
		this.narrator = new Narrator(this, TalkingAt.Left);
		this.consoleUtil = new ConsoleUtil(narrator);
	}

	public void register() {
		//
		while (true) {
			String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			String name = consoleUtil.getValueOf(" name");
			String phoneNumber = consoleUtil.getValueOf(" phone number");
			String nickName = consoleUtil.getValueOf(" nickname");
			String birthDay = consoleUtil.getValueOf(" birthday(yyyy.mm.dd)");

			try {
				MemberCdo memberCdo = new MemberCdo(
						email,
						name,
						phoneNumber,
						nickName,
						birthDay
						);

				String memberId = memberService.registerMember(memberCdo);
				CommunityMember member = memberService.findMemberById(memberId);
				narrator.sayln("\t > Registered member :" + member.toString());

			} catch (MemberDuplicationException | InvalidEmailException e) {
				narrator.sayln(e.getMessage());
			}
		}
	}

	public void find() {
		//
		while (true) {
			//
			String email = consoleUtil.getValueOf("\n Member Email to find(0.Member menu)");
			if (email.equals("0")) {
				return;
			}

			CommunityMember memberFound = memberService.findMemberByEmail(email);
			narrator.sayln("\t > Found member :" + memberFound.toString());
		}
	}

	public void findByName() {
		//
		while(true) {
			String name = consoleUtil.getValueOf("\n member's name to find(0.Member menu)");
			if (name.equals("0")){
				return;
			}

			narrator.sayln("==== Found Member List ====");
			memberService.findMembersByName(name)
					.forEach(member -> narrator.sayln(member.toString()));
		}
	}

	public CommunityMember findOne() {
		//
		CommunityMember member = null;

		while (true) {
			//
			String email = consoleUtil.getValueOf("\n member's email to find(0.Member menu)");
			if (email.equals("0")) {
				return null;
			}

			member = memberService.findMemberByEmail(email);
			narrator.sayln("\t > Found member :" + member.toString());
			break;
		}

		return member;
	}

	public void modify() {
		//
		CommunityMember member = findOne();
		if (member == null) {
			narrator.sayln("\t > No such member");
			return;
		}

		NameValueList nameValueList = new NameValueList();

		String newName = consoleUtil.getValueOf(" new name(Enter. no change)");
		nameValueList.addNameValue("name", newName);

		String newPhoneNumber = consoleUtil.getValueOf(" new phone number(Enter. no change)");
		nameValueList.addNameValue("phoneNumber", newPhoneNumber);

		String newNickName = consoleUtil.getValueOf(" new nickname(Enter. no change)");
		nameValueList.addNameValue("nickName", newNickName);

		String newBirthDay = consoleUtil.getValueOf(" new birthday(yyyy.mm.dd)(Enter. no change)");
		nameValueList.addNameValue("birthDay", newBirthDay);

		try {
			memberService.modifyMember(member.getId(), nameValueList);
			narrator.sayln("\t > Modified member:" + member.toString());
		} catch (NoSuchMemberException | InvalidEmailException e) {
			narrator.sayln(e.getMessage());
		}
	}

	public void remove() {
		//
		CommunityMember member = findOne();
		if (member == null) {
			narrator.sayln("\t > No such member");
			return;
		}

		String confirmStr = consoleUtil.getValueOf("Remove this club? (Y:yes, N:no)");
		if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
			narrator.sayln("\t > Removing a club --> " + member.getName());
			memberService.removeMember(member.getEmail());
		} else {
			narrator.sayln("\t > Remove cancelled, your club is safe. --> " + member.getName());
		}
	}

}
