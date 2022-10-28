package controller;

import dao.AdmDAO;
import dao.AutenticacaoDAO;
import dao.CompradorDAO;
import dao.VendedorDAO;
import model.Usuario;
import view.AutenticacaoView;

public class AutenticacaoController {

	private final AutenticacaoView aView;

	public AutenticacaoController() {
		aView = new AutenticacaoView();
	}

	public Usuario authComprador() {
		AutenticacaoDAO<CompradorDAO> aDao = new AutenticacaoDAO<>(CompradorDAO.class);
		return doAuth(aDao);
	}

	public Usuario authVendedor() {
		AutenticacaoDAO<VendedorDAO> aDao = new AutenticacaoDAO<>(VendedorDAO.class);
		return doAuth(aDao);
	}

	public Usuario authAdministrator() {
		AutenticacaoDAO<AdmDAO> aDao = new AutenticacaoDAO<>(AdmDAO.class);
		return doAuth(aDao);
	}

	private Usuario doAuth(AutenticacaoDAO<?> aDao) {
		Usuario u = null;

		while (u == null) {
			u = aView.login();

			if (aDao.autenticarUsuario(u)) {
				aView.usuarioAutenticado();
			} else {
				aView.usuarioNaoAutenticado();
				u = null;
				boolean retry = aView.retryMenu();

				if (!retry)
					break;
			}
		}

		return u;
	}
}
