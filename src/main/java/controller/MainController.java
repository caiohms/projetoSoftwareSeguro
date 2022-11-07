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
						new MenuOption("Login Comprador", () -> {
							Usuario user = new AutenticacaoController().authComprador();
							new CompradorController().autenticado(user);
						}),
						new MenuOption("Login Vendedor", () -> {
							Usuario user = new AutenticacaoController().authVendedor();
							if (user != null) {
								new VendedorController().autenticado(user);
							}
						}),
						new MenuOption("Login Corretor", () -> {
							Usuario user = new AutenticacaoController().authCorretor();
							if (user != null) {
								new VendedorController().autenticado(user);
							}
						}),
						new MenuOption("Cadastrar Usuario", CadastroController::new),
						new MenuOption("Login Administrador", () -> {
							Usuario user = new AutenticacaoController().authAdministrator();
							if (user != null) {
								new AdmController().autenticado(user);
							}
						})
				)
				.runLoopInView(mainView);
	}
}
