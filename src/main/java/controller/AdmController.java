package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.*;
import lombok.extern.slf4j.Slf4j;
import model.*;
import view.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class AdmController {

	private final Adm admAutenticado;

	private final AdmView admView = new AdmView();
	private final CompradorView compradorView = new CompradorView();
	private final VendedorView vendedorView = new VendedorView();
	private final CorretorView corretorView = new CorretorView();
	private final PropriedadeView propriedadeView = new PropriedadeView();

	private final AdmDAO admDAO = new AdmDAO();
	private final CompradorDAO compradorDao = new CompradorDAO();
	private final VendedorDAO vendedorDao = new VendedorDAO();
	private final CorretorDAO corretorDao = new CorretorDAO();
	private final PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
	private final HistoricoBuscaDAO historicoBuscaDao = new HistoricoBuscaDAO();

	public AdmController() {
		admAutenticado = null;
	}

	private AdmController(Adm adm) {
		// adm autenticado
		this.admAutenticado = adm;
		log.info("Adm autenticado :: " + admAutenticado.toString());

		new OptionsMenu()
				.withTitle("Menu do Administrador")
				.withOptions(
						new MenuOption("Painel compradores", this::painelCompradores),
						new MenuOption("Painel vendedores", this::painelVendedores),
//						new MenuOption("Painel corretores", this::painelCorretores),
						new MenuOption("Painel propriedades", this::painelPropriedades)
				)
				.runLoopInView(admView);
	}

	public void autenticado(Usuario user) {
		Adm adm = admDAO.getAdmFromUsuario(user);
		new AdmController(adm);
	}

	private void painelCompradores() {
		new OptionsMenu()
				.withTitle("Painel Compradores")
				.withOptions(
						new MenuOption("Listar Compradores", this::exibirListaCompradores),
						new MenuOption("Buscar por id", this::buscarCompradorPorId)
				)
				.runLoopInView(admView);
	}

	private void painelVendedores() {
		new OptionsMenu()
				.withTitle("Painel Vendedores")
				.withOptions(
						new MenuOption("Listar Vendedores", this::exibirListaVendedores),
						new MenuOption("Buscar por id", this::buscarVendedorPorId)
				)
				.runLoopInView(admView);
	}

//	private void painelCorretores() {
//		new OptionsMenu()
//				.withTitle("Painel Corretores")
//				.withOptions(
//						new MenuOption("Listar Corretores", this::exibirListaCorretores),
//						new MenuOption("Buscar por id", this::buscarCorretorPorId),
//						new MenuOption("Cadastrar corretor", this::cadastrarCorretor)
//				)
//				.runLoopInView(admView);
//	}

	private void painelPropriedades() {
		new OptionsMenu()
				.withTitle("Painel Propriedades")
				.withOptions(
						new MenuOption("Listar propriedades", this::exibirListaPropriedades),
						new MenuOption("Buscar por id", this::buscarPropriedadePorId)
				)
				.runLoopInView(admView);
	}

	private void exibirListaCompradores() {
		List<Comprador> compradores = compradorDao.getAll();
		compradorView.listarCompradores(compradores);
	}

	private void exibirListaVendedores() {
		List<Vendedor> vendedores = vendedorDao.getAll();
		vendedorView.listarVendedores(vendedores);
	}

//	private void exibirListaCorretores() {
//		//TODO
//	}

	private void exibirListaPropriedades() {
		List<Propriedade> propriedades = propriedadeDAO.getAll();
		propriedadeView.listarPropriedades(propriedades);
	}

	public void buscarCompradorPorId() {
		admView.solicitarIdParaConsulta();
		int id = admView.getNextInt();
		Comprador comprador = compradorDao.get(id);
		admView.admExibirComprador(comprador);

		if (comprador != null) {
			opcoesDoComprador(comprador);
		}
	}

	private void opcoesDoComprador(Comprador comprador) {
		new OptionsMenu()
				.withTitle("Opcoes para Comprador " + comprador.getId())
				.withOptions(
						new MenuOption("Exibir dados", () -> exibirComprador(comprador)),
						new MenuOption("Exibir historico de busca", () -> exibirHistoricoBuscaComprador(comprador)),
						new MenuOption("Editar dados", () -> new CompradorController().alterarDados(comprador)),
						new MenuOption("Excluir", () -> deletarComprador(comprador))
				)
				.runLoopInView(admView);
	}

	// ===== COMPRADOR ===================
	private void exibirComprador(Comprador comprador) {
		admView.admExibirComprador(comprador);
	}

	private void exibirHistoricoBuscaComprador(Comprador comprador) {
		List<HistoricoBusca> historico = historicoBuscaDao.getHistoricoDeComprador(comprador);
		admView.exibirHistoricoBuscaComprador(historico);
	}

	private void deletarComprador(Comprador comprador) {
		boolean confirm = admView.confirmarAcao("Deletar comprador " + comprador.getId() + "(" + comprador.getNome() + "). Essa ação é irreversível.");
		try {
			if (confirm) {
				compradorDao.delete(comprador.getId());
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	//================================

	public void consultarVendedor() {
		int id = admView.getNextInt();
		Vendedor vendedor = vendedorDao.get(id);
		admView.exibirVendedor(vendedor);
	}

	private void buscarVendedorPorId() {
		admView.solicitarIdParaConsulta();
		int id = admView.getNextInt();
		Vendedor vendedor = vendedorDao.get(id);
		admView.exibirVendedor(vendedor);
	}

	private void buscarPropriedadePorId() {
		admView.solicitarIdParaConsulta();
		int id = admView.getNextInt();
		Propriedade propriedade = propriedadeDAO.get(id);
		admView.exibirPropriedade(propriedade);
	}

//	public void cadastrarCorretor() {
//		Corretor novoCadastro = corretorView.realizarCadastro();
//		//save to db
//
//		try {
//			if (corretorDao.save(novoCadastro)) {
//				corretorView.cadastroSuccess();
//			}
//		} catch (SQLException e) {
//			log.error(e.getMessage(), e);
//		}
//	}

//	private void buscarCorretorPorId() {
//		admView.solicitarIdParaConsulta();
//		int id = admView.getNextInt();
//		Corretor corretor = corretorDao.get(id);
//		admView.exibirCorretor(corretor);
//	}

//	public void atualizarDadosCorretor() {
//		//todo revisar
//		int id = admView.getInt();
//		Corretor corretor = corretorView.atualizaCorretor();
//		try {
//			if (corretorDao.update(corretor, id)) corretorView.atualizacaoSuccess();
//		} catch (SQLException e) {
//			log.error(e.getMessage(), e);
//		}
//	}

//	public void deletarCorretor() {
//		//todo revisar
//		int id = admView.getInt();
//		int decision = corretorView.getDeleteOption();
//		try {
//			if (decision == 1) {
//				corretorDao.delete(id);
//			} else {
//				new MainController();
//			}
//		} catch (SQLException e) {
//			log.error(e.getMessage(), e);
//		}
//	}

}
