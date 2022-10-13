package controller;

import dao.AutenticacaoDAO;
import model.Comprador;
import model.Usuario;
import model.Vendedor;
import view.AutenticacaoView;

public class AutenticacaoController {

	private final AutenticacaoView aView;

	public AutenticacaoController() {
		aView = new AutenticacaoView();
	}

	public Usuario authComprador() {
		Usuario user = null;
		AutenticacaoDAO<Comprador> aDao = new AutenticacaoDAO<>(Comprador.class);

		while (user == null) {
			user = aView.login();
			if (aDao.autenticarUsuario(user)) {
				aView.usuarioAutenticado();
			} else {
				aView.usuarioNaoAutenticado();

				if(aView.OpcoesAuth() == 1){
					System.out.println("Opcao cadastrar selecionada");
					new CadastroController();
				}else if(aView.OpcoesAuth() == 2){
					System.out.println("Recuperar senha");

				}
			}
		}

		return user;
	}

	public Usuario authVendedor() {
		Usuario user = null;
		AutenticacaoDAO<Vendedor> aDao = new AutenticacaoDAO<>(Vendedor.class);

		while (user == null) {
			user = aView.login();
			if (aDao.autenticarUsuario(user)) {
				aView.usuarioAutenticado();
			} else {
				aView.usuarioNaoAutenticado();

				if(aView.OpcoesAuth() == 1){
					System.out.println("Opcao cadastrar selecionada");
					new CadastroController();
				}else if(aView.OpcoesAuth() == 2){
					System.out.println("Recuperar senha");

				}


			}
		}

		return user;
	}
}
