package controller;

import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import view.MainView;

@Slf4j
public class MainController {

	private final MainView mainView;

	public MainController() {

		this.mainView = new MainView();
		boolean quit = false;

		while (!quit) {
			Integer userOption = mainView.getOption();
			Usuario user;

			switch (userOption) {
				case 1:
					// Login como comprador
					user = new AutenticacaoController().authComprador();
					new CompradorController().autenticado(user);
					break;
				case 2:
					// Login como Vendedor
					user = new AutenticacaoController().authVendedor();
                    new VendedorController().autenticado(user);
					break;
				case 3:
					// cadastrar
					new CadastroController();
					break;

				case 4:
					// sair
					quit = true;
					break;

				default:
					log.error("Erro ao selecionar opcao");
					break;
			}
		}
	}
}
