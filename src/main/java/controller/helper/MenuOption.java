package controller.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuOption {
	private int index;
	private String optionName;
	private Runnable r;
}
