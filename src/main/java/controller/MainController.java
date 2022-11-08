package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import view.MainView;

@Slf4j
public class MainController {

	public MainController() {
		MainView mainView = new MainView();

		new OptionsMenu()
				.withTitle("LarDoceJar Menu")
				.withOptions(
						new MenuOption("Login Comprador", this::loginComprador),
						new MenuOption("Login Vendedor", this::loginVendedor),
						new MenuOption("Login Corretor", this::loginCorretor),
						new MenuOption("Cadastrar Usuario", CadastroController::new),
						new MenuOption("Login Administrador", this::loginAdministrador)
				)
				.runLoopInView(mainView);
	}

	private void loginComprador() {
		Usuario user = new AutenticacaoController().authComprador();
		if (user != null) {
			new CompradorController().autenticado(user);
		}
	}

	private void loginVendedor() {
		Usuario user = new AutenticacaoController().authVendedor();
		if (user != null) {
			new VendedorController().autenticado(user);
		}
	}

	private void loginCorretor() {
		Usuario user = new AutenticacaoController().authCorretor();
		if (user != null) {
			new CorretorController().autenticado(user);
		}
	}

	private void loginAdministrador() {
		Usuario user = new AutenticacaoController().authAdministrator();
		if (user != null) {
			new AdmController().autenticado(user);
		}
	}
}
