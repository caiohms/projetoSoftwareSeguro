package controller;

import dao.PropriedadeDAO;
import lombok.extern.slf4j.Slf4j;
import model.Propriedade;
import model.Vendedor;
import view.PropriedadeView;

import java.sql.SQLException;

@Slf4j
public class PropriedadeController {

	PropriedadeView propriedadeView = new PropriedadeView();
	PropriedadeDAO propriedadeDAO = new PropriedadeDAO();

	public void venderPropriedade(Vendedor vendedorAutenticado) {
		Propriedade novaPropriedade = null;

		while (!verificarPropriedadeValida(novaPropriedade)) {
			novaPropriedade = propriedadeView.cadastrar();
		}

		novaPropriedade.setVendedor(vendedorAutenticado.getId());

		try {
			if (propriedadeDAO.save(novaPropriedade) != null) {
				propriedadeView.cadastroSuccess();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private boolean verificarPropriedadeValida(Propriedade p) {
		// validacoes adicionais caso necessario
		return p != null;
	}
}
