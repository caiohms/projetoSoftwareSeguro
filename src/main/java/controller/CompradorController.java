package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import dao.CompradorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;
import view.CompradorView;

import java.sql.SQLException;

@Slf4j
public class CompradorController {

	private final CompradorView compradorView = new CompradorView();
	private final CompradorDAO compradorDAO = new CompradorDAO();
	private final Comprador compradorAutenticado;

	public CompradorController() {
		compradorAutenticado = null;
	}

	public CompradorController autenticado(Usuario user) {
		Comprador c = compradorDAO.getCompradorFromUsuario(user);
		return new CompradorController(c);
	}

	private CompradorController(Comprador comprador) {

		log.info("Comprador autenticado :: " + comprador.toString());

		this.compradorAutenticado = comprador;
		int idSaved = compradorAutenticado.getId();

		new OptionsMenu()
				.withTitle("Menu do Comprador")
				.withOptions(
						new MenuOption("Ver Propriedades", () -> {
							//TODO
						}),
						new MenuOption("Alterar Dados", () -> atualizarDados(idSaved)),
						new MenuOption("Consultar Dados", () -> {
							//TODO
						}),
						new MenuOption("Excluir Dados", () -> deletarComprador(idSaved))
				).runLoopInView(compradorView);
	}

	public void realizarCadastro() {
		Comprador novoCadastro = null;

		while (!verificarCadastroValido(novoCadastro)) {
			novoCadastro = compradorView.realizarCadastro();
		}

		try {
			if (compradorDAO.save(novoCadastro)) compradorView.cadastroSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarCadastroValido(Comprador novoCadastro) {
		if (novoCadastro == null) {
			return false;
		}

		if (compradorDAO.checkTakenEmail(novoCadastro.getEmail())) {
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

	public void atualizarDados(int idComprador) {
		//update to db
		Comprador comprador = compradorView.atualizaComprador();
		try {
			if (compradorDAO.update(comprador, idComprador)) compradorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarComprador(int idComprador) {
		//update to db
		int decision = compradorView.getDeleteOption();
		try {
			if (decision == 1) {
				compradorDAO.delete(idComprador);
			} else {
				new MainController();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
