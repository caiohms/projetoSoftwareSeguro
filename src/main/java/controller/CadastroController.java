package controller;

import dao.CompradorDAO;
//import model.Cadastro;
import model.Comprador;
import model.Usuario;
import view.CadastroView;
import view.CompradorView;

public class CadastroController {

    private CadastroView caview;
    private CompradorView ccview;

    public CadastroController(){

        caview = new CadastroView();
        ccview = new CompradorView();
        caview.getCadastroOption();

        if (caview.getCadastroOption() == 1){
            ccview.addCompradorOption();
            new CompradorController().cadastroComprador(ccview);

        }
        else if (caview.getCadastroOption() == 2) {
            //CadastroUser user = new Vendedor();
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

