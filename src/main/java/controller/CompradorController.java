package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.*;
import dao.helper.SearchEngine;
import lombok.extern.slf4j.Slf4j;
import model.*;
import view.CompradorView;
import view.PropriedadeView;
import view.VendedorView;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class CompradorController {

	private final CompradorView compradorView = new CompradorView();

	private final HistoricoBuscaDAO historicoBuscaDao = new HistoricoBuscaDAO();
	private final PropriedadeDAO propriedadeDao = new PropriedadeDAO();
	private final CompradorDAO compradorDao = new CompradorDAO();
	private final VendedorDAO vendedorDao = new VendedorDAO();
	private final EnderecoDAO enderecoDao = new EnderecoDAO();
	private Comprador compradorAutenticado;

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
						new MenuOption("Alterar Dados", this::alterarDados),
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
		String searchString = compradorView.requestSearchString();
		historicoBuscaDao.registrarBusca(this.compradorAutenticado, searchString);
		List<Propriedade> propriedades = new SearchEngine().getPropriedadesPorDescricao(searchString);
		if (propriedades.isEmpty())
			compradorView.noResults();
		else
			new PropriedadeView().listarPropriedades(propriedades);
	}

	private void consultarPropriedadePorId() {
		compradorView.solicitarIdParaConsulta();
		int id = compradorView.getNextInt();
		Propriedade propriedade = propriedadeDao.get(id);
		new PropriedadeView().showPropriedade(propriedade);
	}

	private void consultarPropriedadePorVendedor() {
		compradorView.solicitarIdParaConsulta();
		int vendedorId = compradorView.getNextInt();
		List<Propriedade> propriedades = propriedadeDao.getPropriedadesOfVendedor(vendedorId);
		if (propriedades.isEmpty())
			compradorView.noResults();
		else
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
						new MenuOption("Buscar por nome", this::buscarVendedorPorNome),
						new MenuOption("Consultar por ID", this::consultarVendedorPorId),
						new MenuOption("Listar todos", this::listarTodosVendedores))
				.runLoopInView(compradorView);
	}

	private void buscarVendedorPorNome() {
		String searchString = compradorView.requestSearchString();
		List<Vendedor> vendedores = new SearchEngine().getVendedorPorNome(searchString);
		if (vendedores.isEmpty())
			compradorView.noResults();
		else
			new VendedorView().listarVendedores(vendedores);
	}

	private void consultarVendedorPorId() {
		compradorView.solicitarIdParaConsulta();
		int id = compradorView.getNextInt();
		Vendedor vendedor = vendedorDao.get(id);
		compradorView.showVendedor(vendedor);
	}

	private void listarTodosVendedores() {
		VendedorDAO vendedorDAO = new VendedorDAO();
		List<Vendedor> vendedores = vendedorDAO.getAll();
		compradorView.listarVendedores(vendedores);
	}

	public void realizarCadastro() {
		Comprador novoCadastroComprador = null;

		while (!verificarCadastroValido(novoCadastroComprador)) {
			novoCadastroComprador = compradorView.realizarCadastro();
		}

		try {
			if (compradorDao.save(novoCadastroComprador) != null) compradorView.cadastroSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		oferecerCadastroEndereco(novoCadastroComprador);
	}

	private void oferecerCadastroEndereco(Comprador novoCadastroComprador) {
		compradorView.oferecerCadastroEndereco();
		boolean yes = compradorView.getNextBoolean("");
		if (yes) {
			Endereco endereco = compradorView.realizarCadastroEndereco();
			try {
				endereco = enderecoDao.save(endereco);
				novoCadastroComprador.setEndereco(endereco.getId());
				boolean success = compradorDao.update(novoCadastroComprador.getId(), novoCadastroComprador);
				if (success) compradorView.cadastroSuccess();
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
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

	public void alterarDados() {
		this.compradorAutenticado = this.alterarDados(this.compradorAutenticado);
	}

	public Comprador alterarDados(Comprador c) {
		Comprador compradorAtualizado = compradorView.atualizaComprador(c);
		try {
			if (compradorDao.update(c.getId(), compradorAtualizado)) {
				compradorView.atualizacaoSuccess();
				return compradorAtualizado;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public void deletarComprador() {
		boolean confirm = compradorView.confirmarAcao("Excluir sua conta. Essa a????o ?? irrevers??vel.");
		try {
			if (confirm) {
				compradorDao.delete(this.compradorAutenticado.getId());
			} else {
				new MainController();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
