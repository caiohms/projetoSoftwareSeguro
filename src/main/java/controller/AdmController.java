package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.AdmDAO;
import dao.CompradorDAO;
import dao.CorretorDAO;
import dao.VendedorDAO;
import lombok.extern.slf4j.Slf4j;
import model.*;
import view.AdmView;
import view.CorretorView;

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

	public AdmController autenticado(Usuario user) {
		Adm ad = admDAO.getAdmFromUsuario(user);
		return new AdmController(ad);
	}

	private AdmController(Adm adm) {
		// adm autenticado
		this.admAutenticado = adm;
		log.info("Adm autenticado :: " + admAutenticado.toString());

		new OptionsMenu()
				.withTitle("Menu do Administrador")
				.withOptions(
						new MenuOption(1, "Painel compradores", this::painelCompradores),
						new MenuOption(2, "Painel vendedores", this::painelVendedores)
//						new MenuOption(3, "Painel corretores", this::painelCorretores),
//						new MenuOption(4, "Painel propriedades", this::painelPropriedades)
				)
				.runLoopInView(admView);
	}

	private void painelCompradores() {
		new OptionsMenu()
				.withTitle("Painel Compradores")
				.withOptions(
						new MenuOption(1, "Listar Compradores", this::exibirListaCompradores),
						new MenuOption(2, "Buscar por id", this::buscarCompradorPorId)
				)
				.runLoopInView(admView);
	}

	private void painelVendedores() {
		new OptionsMenu()
				.withTitle("Painel Vendedores")
				.withOptions(
						new MenuOption(1, "Listar Vendedores", this::exibirListaVendedores),
						new MenuOption(2, "Buscar por id", this::buscarCompradorPorId)
				)
				.runLoopInView(admView);
	}


	private void painelCorretores() {
		new OptionsMenu()
				.withTitle("Painel Corretores")
				.withOptions(
						new MenuOption(1, "1. Listar Corretores", this::exibirListaCorretores),
						new MenuOption(2, "2. Buscar por id", this::buscarCorretorPorId),
						new MenuOption(3, "3. Cadastrar corretor", this::cadastrarCorretor)
				);
	}

	private void exibirListaCompradores() {
		//TODO
	}

	private void exibirListaVendedores() {
		//TODO
	}

	private void exibirListaCorretores() {
		//TODO
	}

	public void buscarCompradorPorId() {
		admView.solicitarIdParaConsulta();
		int id = admView.getInt();
		Comprador comprador = compradorDao.get(id);
		admView.exibirComprador(comprador);
	}

	private void buscarCorretorPorId() {
		admView.solicitarIdParaConsulta();
		int id = admView.getInt();
		Corretor corretor = corretorDAO.get(id);
		admView.exibirCorretor(corretor);
	}

	public void cadastrarCorretor() {
		Corretor novoCadastro = corretorView.realizarCadastro();
		//save to db

		try {
			if (corretorDAO.save(novoCadastro)) corretorView.cadastroSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizarDados() {
		//update to db
		int id = admView.getInt();
		Corretor corretor = corretorView.atualizaCorretor();
		try {
			if (corretorDAO.update(corretor, id)) corretorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarCorretor() {
		//update to db
		int id = admView.getInt();
		int decision = corretorView.getDeleteOption();
		try {
			if (decision == 1) {
				corretorDAO.delete(id);
			} else {
				new MainController();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consultarVendedor() {
		int id = admView.getIdVendedor();
		Vendedor vendedor = vendedorDao.get(id);
		admView.consultarVendedor(vendedor);
	}

}
