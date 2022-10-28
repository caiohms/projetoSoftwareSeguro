package controller;

import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import view.MainView;

@Slf4j
public class MainController {

	public MainController() {

		MainView mainView = new MainView();
		boolean quit = false;

		while (!quit) {
			Integer userOption = mainView.getOption();
			Usuario user;

			switch (userOption) {
				case 1:
					// Login como comprador
					user = new AutenticacaoController().authComprador();
					if (user != null) {
						new CompradorController().autenticado(user);
					}
					break;
				case 2:
					// Login como Vendedor
					user = new AutenticacaoController().authVendedor();
					if (user != null) {
						new VendedorController().autenticado(user);
					}
					break;
				case 3:
					// cadastrar
					new CadastroController();
					break;

				case 4:
					// Login como administrador
					user = new AutenticacaoController().authAdministrator();
					if (user != null) {
						new AdmController().autenticado(user);
					}
					break;
				case 5:
					// sair
					quit = true;
					mainView.showQuit();
					break;

				default:
					log.error("Erro ao selecionar opcao");
					break;
			}
		}
	}
}
