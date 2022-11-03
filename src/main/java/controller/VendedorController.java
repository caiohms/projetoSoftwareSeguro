package controller;

import dao.PropriedadeDAO;
import dao.VendedorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Propriedade;
import model.Usuario;
import model.Vendedor;
import view.VendedorView;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class VendedorController {

	private final VendedorView vendedorView = new VendedorView();
	private final VendedorDAO vendedorDAO = new VendedorDAO();
	private final Vendedor vendedorAutenticado;

	public VendedorController() {
		vendedorAutenticado = null;
	}

	private VendedorController(Vendedor vendedor) {
		// chamar apenas para vendedores autenticados
		log.info("Vendedor autenticado :: " + vendedor.toString());
		this.vendedorAutenticado = vendedor;

		boolean exit = false;
		while (!exit) {
			int option = vendedorView.getVendedorOption();
			switch (option) {
				case 1:
//                Consultar minhas propriedades a venda
					List<Propriedade> propriedades = PropriedadeDAO.getPropriedadesOfVendedor(vendedorAutenticado);
					break;
				case 2:
//                Vender nova propriedade
					new PropriedadeController().venderPropriedade(vendedorAutenticado);
					break;
				case 3:
//                Alterar cadastro do vendedor
					atualizarDados();
					break;
				case 4:
//                Consultar cadastro do vendedor
					consultarDados();

					break;
				case 5:
//                Excluir Dados do vendedor
					deletarComprador();
					break;
				default:
					exit = true;
					break;
			}
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

	private void consultarDados() {
		vendedorView.exibirDados(this.vendedorAutenticado);
		vendedorView.confirmToContinue();
	}

	public void atualizarDados() {
		//update to db
		Vendedor vendedor = vendedorView.atualizarVendedor();
		try {
			if (vendedorDAO.update(vendedor, vendedorAutenticado.getId()))
				vendedorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarComprador() {
		//update to db
		int decision = vendedorView.getDeleteOption();
		try {
			if (decision == 1) {
				vendedorDAO.delete(vendedorAutenticado.getId());
			} else {
				vendedorView.noChangesWereMade();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
