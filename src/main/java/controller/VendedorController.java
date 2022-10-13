package controller;

import dao.VendedorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import model.Vendedor;
import view.VendedorView;

import java.sql.SQLException;

@Slf4j
public class VendedorController {

    private final VendedorView vendedorView;
    private final VendedorDAO vendedorDAO;
    private final Vendedor vendedorAutenticado;

    public VendedorController() {
        vendedorView = new VendedorView();
        vendedorDAO = new VendedorDAO();
        vendedorAutenticado = null;
    }

    private VendedorController(Vendedor vendedor) {

        log.info("Vendedor autenticado :: " + vendedor.toString());

        // chamar apenas para vendedores autenticados
        vendedorView = new VendedorView();
        vendedorDAO = new VendedorDAO();
        this.vendedorAutenticado = vendedor;
        int idSaved = vendedorAutenticado.getId();
        int option = vendedorView.getVendedorOption();

        switch (option) {
            case 1:
//                Vender Imóvel
                break;
            case 2:
//                Atualizar Dados do vendedor
                new VendedorController().atualizarDados(idSaved);
                break;
            case 3:
//                Consultar Dados do vendedor
                break;
            case 4:
//                Excluir Dados do vendedor
                new VendedorController().deletarComprador(idSaved);
                break;
            default:
                break;
        }
    }

    public VendedorController autenticado(Usuario user) {
        Vendedor v = vendedorDAO.getVendedorFromUsuario(user);
        return new VendedorController(v);
    }

    public void realizarCadastro() {
        Vendedor novoCadastro = vendedorView.realizarCadastro();
        //save to db

        try {
            if (vendedorDAO.save(novoCadastro))
                vendedorView.cadastroSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarDados(int idVendedor){
        //update to db
        Vendedor vendedor = vendedorView.atualizarVendedor();
        try {
            if (vendedorDAO.update(vendedor, idVendedor))
                vendedorView.atualizacaoSuccess();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarComprador(int idVendedor){
        //update to db
        int decision = vendedorView.getDeleteOption();
        try {
            if (decision == 1) {
                vendedorDAO.delete(idVendedor);
            }
            else {
                new MainController();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
