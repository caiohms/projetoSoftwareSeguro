package controller;

import dao.CompradorDAO;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;
import view.CompradorView;

import java.sql.SQLException;

@Slf4j
public class CompradorController {

	private final CompradorView compradorView;
	private final CompradorDAO compradorDAO;
	private final Comprador compradorAutenticado;

	public CompradorController() {
		compradorView = new CompradorView();
		compradorDAO = new CompradorDAO();
		compradorAutenticado = null;
	}

	private CompradorController(Comprador comprador) {

		log.info("Comprador autenticado :: " + comprador.toString());

		// chamar apenas para compradores autenticados
		compradorView = new CompradorView();
		compradorDAO = new CompradorDAO();
		this.compradorAutenticado = comprador;
		int idSaved = compradorAutenticado.getId();
		int option = compradorView.getCompradorOption();

		switch (option) {
			case 1:
//                Ver propriedades
				break;
			case 2:
//                Atualizar Dados do comprador
				new CompradorController().atualizarDados(idSaved);
				break;
			case 3:
//                Consultar Dados do comprador
				break;
			case 4:
//                Excluir Dados do comprador
                new CompradorController().deletarComprador(idSaved);
				break;
			default:
				break;
		}
	}

	public CompradorController autenticado(Usuario user) {
		Comprador c = compradorDAO.getCompradorFromUsuario(user);
		return new CompradorController(c);
	}

	public void realizarCadastro() {
		Comprador novoCadastro = compradorView.realizarCadastro();
		//save to db

		try {
			if (compradorDAO.save(novoCadastro))
				compradorView.cadastroSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void atualizarDados(int idComprador){
		//update to db
		Comprador comprador = compradorView.atualizaComprador();
		try {
			if (compradorDAO.update(comprador, idComprador))
				compradorView.atualizacaoSuccess();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarComprador(int idComprador){
		//update to db
		int decision = compradorView.getDeleteOption();
		try {
			if (decision == 1) {
				compradorDAO.delete(idComprador);
			}
			else {
				new MainController();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

