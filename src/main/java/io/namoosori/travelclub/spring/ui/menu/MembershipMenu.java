package io.namoosori.travelclub.spring.ui.menu;

import io.namoosori.travelclub.spring.ui.console.MembershipConsole;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class MembershipMenu {
	//
	@Autowired
	private MembershipConsole membershipConsole;

	private Scanner scanner;
	private Narrator narrator;

	public MembershipMenu() {
		//
		this.scanner = new Scanner(System.in); 
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public void show() {
		//
		int inputNumber = 0;

		while(true){
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				membershipConsole.findClub();
				break;
			case 2:
				membershipConsole.add();
				break;
			case 3:
				membershipConsole.find();
				break;
			case 4:
				membershipConsole.modify();
				break;
			case 5:
				membershipConsole.remove();
				break;
			case 0:
				return;

			default:
				narrator.sayln("Choose again!");
			}
		}
	}

	private void displayMenu() {
		//
		narrator.sayln("");
		narrator.sayln("..............................");
		if (membershipConsole.hasCurrentClub()) {
			narrator.sayln(" Membership menu for[" + membershipConsole.requestCurrentClubName() + "]");
		} else {
			narrator.sayln(" Membership menu ");
		}
		narrator.sayln("..............................");
		narrator.sayln(" 1. Find a club");
		narrator.sayln(" 2. Add a membership");
		narrator.sayln(" 3. Find a membership");
		narrator.sayln(" 4. Modify a membership");
		narrator.sayln(" 5. Remove a membership");
		narrator.sayln("..............................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("..............................");
	}

	private int selectMenu() {
		//
		narrator.say("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 5) {
			scanner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's an invalid number --> " + menuNumber);
			return -1;
		}
	}
}