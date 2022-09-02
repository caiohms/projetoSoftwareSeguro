package controller;

import dao.AutenticacaoDAO;
import model.Usuario;
import view.AutenticacaoView;

public class AutenticacaoController {

    private AutenticacaoView aview;
    private Usuario usuario;
    private AutenticacaoDAO adao;

    public AutenticacaoController(){

        aview = new AutenticacaoView();
        Usuario usuarioSendoEnviadoParaOFront = new Usuario();
        usuario = aview.login(usuarioSendoEnviadoParaOFront);

        adao = new AutenticacaoDAO();
        if(adao.autenticarUsuario(usuario)){
            aview.usuarioAutenticado();
            new MenuController(usuario);
        }
        else {
            aview.usuarioNaoAutenticado();
        }
    }

}
