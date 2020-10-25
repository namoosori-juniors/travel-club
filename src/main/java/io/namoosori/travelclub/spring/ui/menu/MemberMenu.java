package io.namoosori.travelclub.spring.ui.menu;

import io.namoosori.travelclub.spring.ui.console.MemberConsole;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;

import java.util.Scanner;

public class MemberMenu {
	//
	private MemberConsole memberConsole;
	
	private Scanner scanner;
	private Narrator narrator;

	public MemberMenu(MemberConsole memberConsole) {
		//
		this.memberConsole = memberConsole;

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
				memberConsole.register(); 
				break;
			case 2:
				memberConsole.find();
				break;
			case 3:
				memberConsole.findByName();
				break;
			case 4:
				memberConsole.modify();
				break;
			case 5:
				memberConsole.remove();
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
		narrator.sayln(" Member menu ");
		narrator.sayln("..............................");
		narrator.sayln(" 1. Register");
		narrator.sayln(" 2. Find");
		narrator.sayln(" 3. Find by name");
		narrator.sayln(" 4. Modify");
		narrator.sayln(" 5. Remove");
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