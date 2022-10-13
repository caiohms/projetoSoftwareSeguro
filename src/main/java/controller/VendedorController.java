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

        log.info("Comprador autenticado :: " + vendedor.toString());

        // chamar apenas para vendedores autenticados
        vendedorView = new VendedorView();
        vendedorDAO = new VendedorDAO();
        this.vendedorAutenticado = vendedor;

        vendedorView.getVendedorOption();
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
}
