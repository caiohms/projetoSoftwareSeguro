package controller;

import dao.CompradorDAO;
import model.Comprador;
import model.Usuario;
import view.CompradorView;

public class CompradorController {

	private final CompradorView cView;
	private final CompradorDAO cDao;
	private Comprador comprador;

	public CompradorController(Usuario user) {

		cView = new CompradorView();
		cDao = new CompradorDAO();

		loadUser(user);

		cView.getCompradorOption();

//        cdao = new CompradorDAO();
//        if(cdao.autenticarUsuario(comprador)){
//            cview.usuarioAutenticado();
//            new MenuController(comprador);
//        }
//        else {
//            cview.usuarioNaoAutenticado();
	}

	private void loadUser(Usuario user) {
		this.comprador = cDao.getCompradorFromUsuario(user);
	}
}

