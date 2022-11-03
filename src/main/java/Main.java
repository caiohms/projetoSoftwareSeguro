import controller.MainController;

public class Main {

	public static void main(String[] args) {
		new MainController();
	}

//	public static void main(String[] args) throws IOException {
//// Setup terminal and screen layers
//
//		Terminal terminal = new DefaultTerminalFactory().setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.getDefaultOfSize(24)).createTerminal();
//		Screen screen = new TerminalScreen(terminal);
//		screen.startScreen();
//
//
//		// Create panel to hold components
//		Panel panel = new Panel();
//		panel.setLayoutManager(new GridLayout(2));
//
//		final Label lblOutput = new Label("");
//
//		panel.addComponent(new Label("Banoel Mina"));
//
//
//		// Create window to hold the panel
//		BasicWindow window = new BasicWindow();
//		window.setComponent(panel);
//
//		// Create gui and start gui
//		MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
//		gui.addWindowAndWait(window);
//
//	}
}