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

	private final PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
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
						new MenuOption("Listar minhas propriedades cadastradas", this::listarMinhasPropriedades),
						new MenuOption("Editar propriedade cadastrada", this::editarPropriedadeCadastrada),
						new MenuOption("Anunciar nova propriedade", () -> new PropriedadeController().venderPropriedade(vendedorAutenticado)),
						new MenuOption("Alterar meus dados", this::atualizarDados),
						new MenuOption("Consultar meus dados", this::consultarDados),
						new MenuOption("Excluir meu cadastro", this::deletarVendedor))
				.runLoopInView(vendedorView);
	}

	private void listarMinhasPropriedades() {
		List<Propriedade> propriedades = propriedadeDAO.getPropriedadesOfVendedor(vendedorAutenticado.getId());
		propriedadeView.listarPropriedades(propriedades);
	}

	private void editarPropriedadeCadastrada() {
		vendedorView.solicitarIdParaConsulta();
		int id = vendedorView.getNextInt();
		Propriedade propriedade = propriedadeDAO.get(id);

		Propriedade propriedadeEditada = vendedorView.editarPropriedadeCadastrada(propriedade);

		if (propriedadeDAO.update(id, propriedadeEditada))
			vendedorView.atualizacaoSuccess();
	}

	public VendedorController autenticado(Usuario user) {
		Vendedor v = vendedorDAO.getVendedorFromUsuario(user);
		return new VendedorController(v);
	}

	public void realizarCadastro() {
		Vendedor novoCadastro = null;

		while (verificarCadastroValido(novoCadastro)) {
			novoCadastro = vendedorView.realizarCadastro();
		}

		try {
			if (vendedorDAO.save(novoCadastro) != null) {
				vendedorView.cadastroSuccess();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarCadastroValido(Vendedor novoCadastro) {
		if (novoCadastro == null) {
			return true;
		}

		if (vendedorDAO.checkTakenEmail(novoCadastro.getEmail())) {
			vendedorView.sendEmailAlreadyExists();
			return true;
		}

		try {
			Integer.parseInt(novoCadastro.getIdade());
		} catch (NumberFormatException e) {
			vendedorView.sendInvalidAge();
			return true;
		}

		return false;
	}

	private void consultarDados() {
		vendedorView.exibirDados(this.vendedorAutenticado);
		vendedorView.confirmToContinue();
	}

	public void atualizarDados() {
		//update to db
		Vendedor vendedor = vendedorView.atualizarVendedor();
		try {
			if (vendedorDAO.update(vendedorAutenticado.getId(), vendedor))
				vendedorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarVendedor() {
		boolean confirm = vendedorView.confirmarAcao("Excluir sua conta. Essa ação é irreversível.");
		try {
			if (confirm) {
				vendedorDAO.delete(vendedorAutenticado.getId());
			} else {
				vendedorView.noChangesWereMade();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
