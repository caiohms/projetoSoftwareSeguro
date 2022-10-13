package controller;

import view.CadastroView;
import view.CompradorView;

public class CadastroController {

	private final CadastroView cadastroView;
	private final CompradorView compradorView;

	public CadastroController() {

		cadastroView = new CadastroView();
		compradorView = new CompradorView();

		int option = cadastroView.getCadastroOption();

		switch (option) {
			case 1:
//                Opcao 1 - Cadastrar como Comprador
				new CompradorController().realizarCadastro();
				break;
			case 2:
//                Opcao 2 - Cadastrar como Vendedor
//				CadastroUser user = new Vendedor();

				break;
			default:
				break;
		}


//        cdao = new CompradorDAO();
//        if(cdao.autenticarUsuario(comprador)){
//            cview.usuarioAutenticado();
//            new MenuController(comprador);
//        }
//        else {
//            cview.usuarioNaoAutenticado();
	}
}

