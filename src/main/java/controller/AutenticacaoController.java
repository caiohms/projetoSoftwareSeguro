package controller;

import dao.AutenticacaoDAO;
import model.Usuario;
import view.AutenticacaoView;

public class AutenticacaoController {

    private AutenticacaoView aview;
    private AutenticacaoDAO adao;
    private Usuario user;

    public AutenticacaoController(){

        aview = new AutenticacaoView();
        Usuario user = new Usuario();
        while (user == null) {
            user = aview.login(user);

            adao = new AutenticacaoDAO();
            if (adao.autenticarUsuario(user)) {
                aview.usuarioAutenticado();

            } else {
                aview.usuarioNaoAutenticado();
            }
        }
    }

    public Usuario getUsuario() {
        return this.user;
    }
}
