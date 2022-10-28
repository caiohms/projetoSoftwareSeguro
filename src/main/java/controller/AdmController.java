package controller;

import dao.AdmDAO;
import dao.CorretorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Adm;
import model.Corretor;
import model.Usuario;
import view.AdmView;
import view.CorretorView;

import java.sql.SQLException;

@Slf4j
public class AdmController {

	private final AdmView admView;
	private final AdmDAO admDAO;
	private final Adm admAutenticado;
	private final CorretorView corretorView;
	private final CorretorDAO corretorDAO;


	public AdmController() {
		admView = new AdmView();
		admDAO = new AdmDAO();
		corretorView = new CorretorView();
		corretorDAO = new CorretorDAO();
		admAutenticado = null;
	}

	private AdmController(Adm adm) {

		log.info("Adm autenticado :: " + adm.toString());

		// chamar apenas para compradores autenticados
		admView = new AdmView();
		admDAO = new AdmDAO();
		corretorView = new CorretorView();
		corretorDAO = new CorretorDAO();
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
				break;
			case 4:
//                Consultar Dados do comprador
				break;
			case 5:
//                Consultar Dados do vendedor
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

	public void insertOfAdm() {
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

	public void atualizarDados() {
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

	public void deletarCorretor() {
		//update to db
		int id = admView.getIdCorretor();
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
}
