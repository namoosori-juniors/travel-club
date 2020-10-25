package io.namoosori.travelclub.spring.ui.menu;

import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;

import java.util.Scanner;

public class MainMenu {
	//
	private ClubMenu clubMenu;
	private MemberMenu memberMenu;
	private MembershipMenu membershipMenu;
	
	private Scanner scanner;
	private Narrator narrator;

	public MainMenu(ClubMenu clubMenu, MemberMenu memberMenu, MembershipMenu membershipMenu) {
		//
		this.clubMenu = clubMenu;
		this.memberMenu = memberMenu;
		this.membershipMenu = membershipMenu;
		
		this.scanner = new Scanner(System.in);
		this.narrator = new Narrator(this, TalkingAt.Left);
	}

	public void show() {
		//
		int inputNumber = 0;

		while (true) {
			displayMenu();
			inputNumber = selectMenu();

			switch (inputNumber) {
			//
			case 1:
				clubMenu.show();
				break;
			case 2:
				memberMenu.show(); 
				break;
			case 3:
				membershipMenu.show();
				break;
			case 0:
				this.exitProgram();

			default:
				narrator.sayln("Choose again!");
			}
		}
	}

	private void displayMenu() {
		//
		narrator.sayln("");
		narrator.sayln("..............................");
		narrator.sayln(" Main menu ");
		narrator.sayln("..............................");
		narrator.sayln(" 1. Club menu");
		narrator.sayln(" 2. Member menu");
		narrator.sayln(" 3. Membership menu");
		narrator.sayln("..............................");
		narrator.sayln(" 0. Exit program");
		narrator.sayln("..............................");
	}

	private int selectMenu() {
		//
		narrator.say("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 3) {
			scanner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's an invalid number --> " + menuNumber);
			return -1;
		}
	}
	
	private void exitProgram() {
		//
		narrator.sayln("Program exit. Bye...");
		scanner.close();
		System.exit(0);
	}	
}