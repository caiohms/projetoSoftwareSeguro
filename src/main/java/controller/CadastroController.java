package controller;

//import dao.CadastroDAO;
//import model.Cadastro;
import view.CadastroView;

public class CadastroController {

    private CadastroView caview;
//    private Cadastro comprador;
//    private CadastroDAO cadao;
//    private Usuario user;
    public CadastroController(){

        caview = new CadastroView();

        if (caview.getCadastroOption() == 1){
            System.out.println("Cadastrar Comprador");
            //CadastroUser user = new Comprador();
        }
        else if (caview.getCadastroOption() == 2) {
            System.out.println("Cadastro Vendedor");
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

