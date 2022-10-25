package controller;

import controller.MainController;
import dao.*;

import lombok.extern.slf4j.Slf4j;
import model.*;
import view.AdmView;
import view.CorretorView;
import dao.CorretorDAO;
import view.VendedorView;

import java.sql.SQLException;

@Slf4j
public class AdmController {

    private final AdmView admView = new AdmView();
    private final AdmDAO admDAO = new AdmDAO();
    private final Adm admAutenticado;
    private final CorretorView corretorView = new CorretorView();
    private final CorretorDAO corretorDAO = new CorretorDAO();
    private final VendedorDAO vendedorDao = new VendedorDAO();
    private final CompradorDAO compradorDao = new CompradorDAO();

    public AdmController() {
        admAutenticado = null;
    }

    private AdmController(Adm adm) {

        log.info("Adm autenticado :: " + adm.toString());

        // chamar apenas para compradores autenticados
        this.admAutenticado = adm;
        int idSaved = admAutenticado.getId();
        int option = admView.getAdmOption();

        switch (option) {
            case 1:
//                Cadastrar Corretor
                new AdmController().realizarCadastro();
                break;
            case 2:
//                Atualizar Dados do corretor
                new AdmController().atualizarDados();
                break;
            case 3:
//                Consultar Dados do corretor
                new AdmController().consultarCorretor();
                break;
            case 4:
//                Consultar Dados do comprador
                new AdmController().consultarComprador();
                break;
            case 5:
//                Consultar Dados do vendedor
                new AdmController().consultarVendedor();
                break;
            case 6:
//                Excluir Corretor
                new AdmController().deletarCorretor();
                break;
            case 7:
//                Consultar Dados do comprador
                break;
            default:
                break;
        }
    }

    public AdmController autenticado(Usuario user) {
        Adm ad = admDAO.getAdmFromUsuario(user);
        return new AdmController(ad);
    }
    public void insertOfAdm(){
        int decision = corretorView.getDeleteOption();
    }
    public void realizarCadastro() {
        Corretor novoCadastro = corretorView.realizarCadastro();
        //save to db

        try {
            if (corretorDAO.save(novoCadastro))
                corretorView.cadastroSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizarDados(){
        //update to db
        int id = admView.getIdCorretor();
        Corretor corretor = corretorView.atualizaCorretor();
        try {
            if (corretorDAO.update(corretor, id))
                corretorView.atualizacaoSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultarCorretor() {
        int id = admView.getIdCorretor();
        Corretor corretor = corretorDAO.get(id);
        admView.consultarCorretor(corretor);
    }

    public void consultarComprador(){
        int id = admView.getIdComprador();
        Comprador comprador = compradorDao.get(id);
        admView.consultarComprador(comprador);
    }

    public void consultarVendedor(){
        int id = admView.getIdVendedor();
        Vendedor vendedor = vendedorDao.get(id);
        admView.consultarVendedor(vendedor);
    }

    public void deletarCorretor(){
        //update to db
        int id = admView.getIdCorretor();
        int decision = corretorView.getDeleteOption();
        try {
            if (decision == 1) {
                corretorDAO.delete(id);
            }
            else {
                new MainController();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}