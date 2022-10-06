package controller;

import dao.CompradorDAO;
import model.Comprador;
import view.CompradorView;

public class CompradorController {

    private CompradorView cview;
    private Comprador comprador;
    private CompradorDAO cdao;

    public CompradorController(){

        cview = new CompradorView();
        Comprador usuarioSendoEnviadoParaOFront = new Comprador();
        cview.getCompradorOption();

//        cdao = new CompradorDAO();
//        if(cdao.autenticarUsuario(comprador)){
//            cview.usuarioAutenticado();
//            new MenuController(comprador);
//        }
//        else {
//            cview.usuarioNaoAutenticado();
        }
    }

