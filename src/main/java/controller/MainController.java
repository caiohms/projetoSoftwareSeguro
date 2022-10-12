package controller;

import model.Usuario;
import view.MainView;

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
					new CompradorController(user);
					break;
				case 2:
					// Login como Vendedor
					user = new AutenticacaoController().authVendedor();
//                    new VendedorController(user);
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
					System.err.println("Erro ao selecionar opcao");
					break;
			}
		}
	}
}
