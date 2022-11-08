package controller.helper;

import lombok.Getter;
import view.View;

import java.util.Scanner;

@Getter
public class OptionsMenu {
	private final Scanner sc = new Scanner(System.in);
	private String title;
	private MenuOption[] optionsList;

	public OptionsMenu withTitle(String title) {
		this.title = title;
		return this;
	}

	public OptionsMenu withOptions(MenuOption... options) {
		this.optionsList = options;
		return this;
	}

	public void runLoopInView(View view) {
		boolean quit = false;

		while (!quit) {
			view.displayOptionsMenu(this);
			int option = view.getLimitedOption(1, optionsList.length + 1);
			if (option == optionsList.length + 1) {
				quit = true;
			} else {
				optionsList[option - 1].getR().run();
			}
		}
	}

	public void runOnceInView(View view) {
		view.displayOptionsMenu(this);
		int option = view.getLimitedOption(1, optionsList.length + 1);
		if (option != optionsList.length + 1) {
			optionsList[option - 1].getR().run();
		}
	}

}
