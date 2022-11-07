package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.PropriedadeDAO;
import dao.VendedorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Propriedade;
import model.Usuario;
import model.Vendedor;
import view.PropriedadeView;
import view.VendedorView;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class VendedorController {

	private final VendedorView vendedorView = new VendedorView();
	private final PropriedadeView propriedadeView = new PropriedadeView();
	private final VendedorDAO vendedorDAO = new VendedorDAO();
	private final Vendedor vendedorAutenticado;

	public VendedorController() {
		vendedorAutenticado = null;
	}

	private VendedorController(Vendedor vendedor) {
		// chamar apenas para vendedores autenticados
		log.info("Vendedor autenticado :: " + vendedor.toString());
		this.vendedorAutenticado = vendedor;

		new OptionsMenu()
				.withTitle("Menu do Vendedor")
				.withOptions(
						new MenuOption("Consultar minhas propriedades cadastradas", this::consultarMinhasPropriedades),
						new MenuOption("Anunciar nova propriedade", () -> new PropriedadeController().venderPropriedade(vendedorAutenticado)),
						new MenuOption("Alterar meus dados", this::atualizarDados),
						new MenuOption("Consultar meus dados", this::consultarDados),
						new MenuOption("Excluir meu cadastro", this::deletarComprador)
				).runLoopInView(vendedorView);
	}

	private void consultarMinhasPropriedades() {
		List<Propriedade> propriedades = PropriedadeDAO.getPropriedadesOfVendedor(vendedorAutenticado);
		propriedadeView.listarPropriedades(propriedades);
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
