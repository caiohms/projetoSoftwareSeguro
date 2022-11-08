package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.CompradorDAO;
import dao.PropriedadeDAO;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Propriedade;
import model.Usuario;
import view.CompradorView;
import view.PropriedadeView;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class CompradorController {

	private final CompradorView compradorView = new CompradorView();

	private final PropriedadeDAO propriedadeDao = new PropriedadeDAO();
	private final CompradorDAO compradorDao = new CompradorDAO();
	private final Comprador compradorAutenticado;

	public CompradorController() {
		compradorAutenticado = null;
	}

	private CompradorController(Comprador comprador) {
		log.info("Comprador autenticado :: " + comprador.toString());
		this.compradorAutenticado = comprador;

		new OptionsMenu()
				.withTitle("Menu do Comprador")
				.withOptions(
						new MenuOption("Consultar Propriedades", this::consultarPropriedades),
						new MenuOption("Consultar Vendedores", this::consultarVendedores),
						new MenuOption("Alterar Dados", this::atualizarDados),
						new MenuOption("Consultar Dados", this::consultarDados),
						new MenuOption("Excluir Dados", this::deletarComprador))
				.runLoopInView(compradorView);
	}


	public void autenticado(Usuario user) {
		Comprador c = compradorDao.getCompradorFromUsuario(user);
		new CompradorController(c);
	}

	private void consultarDados() {
		compradorView.exibirMeusDados(this.compradorAutenticado);
	}

	private void consultarPropriedades() {
		new OptionsMenu()
				.withTitle("Consulta de propriedades")
				.withOptions(
						new MenuOption("Realizar busca", this::realizarBuscaPropriedades),
						new MenuOption("Consultar por ID", this::consultarPropriedadePorId),
						new MenuOption("Consultar por vendedor", this::consultarPropriedadePorVendedor),
						new MenuOption("Exibir todas", this::exibirTodasPropriedades))
				.runLoopInView(compradorView);
	}

	private void realizarBuscaPropriedades() {
//todo
	}

	private void consultarPropriedadePorId() {
//todo
	}

	private void consultarPropriedadePorVendedor() {
		compradorView.solicitarIdParaConsulta();
		int vendedorId = compradorView.getInt();
		List<Propriedade> propriedades = propriedadeDao.getPropriedadesOfVendedor(vendedorId);
		new PropriedadeView().listarPropriedades(propriedades);
	}

	private void exibirTodasPropriedades() {
		PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
		List<Propriedade> propriedades = propriedadeDAO.getAll();
		new PropriedadeView().listarPropriedades(propriedades);
	}

	private void consultarVendedores() {
		new OptionsMenu()
				.withTitle("Consulta de vendedores")
				.withOptions(
						new MenuOption("Realizar busca", this::realizarBuscaPropriedades),
						new MenuOption("Consultar por ID", this::consultarPropriedadePorId),
						new MenuOption("Consultar por vendedor", this::consultarPropriedadePorVendedor),
						new MenuOption("Exibir todas", this::exibirTodasPropriedades))
				.runLoopInView(compradorView);
	}

	public void realizarCadastro() {
		Comprador novoCadastro = null;

		while (!verificarCadastroValido(novoCadastro)) {
			novoCadastro = compradorView.realizarCadastro();
		}

		try {
			if (compradorDao.save(novoCadastro)) compradorView.cadastroSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarCadastroValido(Comprador novoCadastro) {
		if (novoCadastro == null) {
			return false;
		}

		if (compradorDao.checkTakenEmail(novoCadastro.getEmail())) {
			compradorView.sendEmailAlreadyExists();
			return false;
		}

		try {
			Integer.parseInt(novoCadastro.getIdade());
		} catch (NumberFormatException e) {
			compradorView.sendInvalidAge();
			return false;
		}

		return true;
	}

	public void atualizarDados() {
		Comprador comprador = compradorView.atualizaComprador(this.compradorAutenticado);
		try {
			if (compradorDao.update(comprador, this.compradorAutenticado.getId())) compradorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarComprador() {
		int decision = compradorView.getDeleteOption();
		try {
			if (decision == 1) {
				compradorDao.delete(this.compradorAutenticado.getId());
			} else {
				new MainController();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
