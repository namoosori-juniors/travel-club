package io.namoosori.travelclub.spring.ui.menu;

import io.namoosori.travelclub.spring.ui.console.ClubConsole;
import io.namoosori.travelclub.spring.util.helper.Narrator;
import io.namoosori.travelclub.spring.util.helper.TalkingAt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class ClubMenu {
	//
	@Autowired
	private ClubConsole clubConsole;
	
	private Scanner scanner;
	private Narrator narrator;

	public ClubMenu() {
		//
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
				clubConsole.register(); 
				break;
			case 2:
				clubConsole.find();
				break;
			case 3:
				clubConsole.modify(); 
				break;
			case 4:
				clubConsole.remove(); 
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
		narrator.sayln(" Club menu ");
		narrator.sayln("..............................");
		narrator.sayln(" 1. Register");
		narrator.sayln(" 2. Find");
		narrator.sayln(" 3. Modify");
		narrator.sayln(" 4. Remove");
		narrator.sayln("..............................");
		narrator.sayln(" 0. Previous");
		narrator.sayln("..............................");
	}

	private int selectMenu() {
		//
		narrator.say("Select: ");
		int menuNumber = scanner.nextInt();

		if (menuNumber >= 0 && menuNumber <= 4) {
			scanner.nextLine();
			return menuNumber;
		} else {
			narrator.sayln("It's an invalid number --> " + menuNumber);
			return -1;
		}
	}
}