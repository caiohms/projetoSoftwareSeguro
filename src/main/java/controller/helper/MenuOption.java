package controller.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuOption {
	private String optionName;
	private Runnable r;
}
