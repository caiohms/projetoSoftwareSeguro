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

		compradorView.getCompradorOption();

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
}

