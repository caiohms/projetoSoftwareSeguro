package controller;

import dao.CompradorDAO;
import form.CompradorForm;
import model.Comprador;
import model.Usuario;
import view.CadastroView;
import view.CompradorView;
import form.CompradorForm;

public class CompradorController {

	private final CompradorView cView;
	private final CompradorDAO cDao;
	private Comprador comprador;
	private CompradorForm form;
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

	public void cadastroComprador(CompradorForm form){
		new Comprador(form);
	}
	private void loadUser(Usuario user) {
		this.comprador = cDao.getCompradorFromUsuario(user);
	}
}

