package controller.helper;

import lombok.Getter;
import view.AdmView;

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

	public void runLoopInView(AdmView admView) {
		boolean quit = false;

		while (!quit) {
			admView.displayOptionsMenu(this);
			int option = admView.getLimitedOption(1, optionsList.length + 1);
			if (option == optionsList.length + 1) {
				quit = true;
			} else {
				optionsList[option - 1].getR().run();
			}
		}
	}

}
