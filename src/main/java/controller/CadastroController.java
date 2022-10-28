package controller;

import view.CadastroView;

public class CadastroController {

	public CadastroController() {

		CadastroView cadastroView = new CadastroView();
		int option = cadastroView.getCadastroOption();

		switch (option) {
			case 1:
//                Opcao 1 - Cadastrar como Comprador
				new CompradorController().realizarCadastro();
				break;
			case 2:
//                Opcao 2 - Cadastrar como Vendedor
				new VendedorController().realizarCadastro();
				break;
			default:
				break;
		}


	}
}

