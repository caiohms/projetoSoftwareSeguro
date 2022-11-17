package controller;

import dao.PropriedadeDAO;
import lombok.extern.slf4j.Slf4j;
import model.Imagem;
import model.Propriedade;
import model.Vendedor;
import view.PropriedadeView;

import java.sql.SQLException;
import java.util.List;

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

		Propriedade saved = null;
		try {
			saved = propriedadeDAO.save(novaPropriedade);
			if (saved != null) {
				propriedadeView.cadastroSuccess();
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		oferecerCadastroImagens(saved);
	}

	private void oferecerCadastroImagens(Propriedade p) {
		boolean yes = propriedadeView.getNextBoolean("Deseja cadastrar imagens? [y/n]");
		if (yes) {
			List<Imagem> imagens = propriedadeView.cadastrarImagens(p);
			boolean ok = propriedadeDAO.saveImages(imagens);
			if (ok) propriedadeView.cadastroSuccess();
		}
	}

	private boolean verificarPropriedadeValida(Propriedade p) {
		// validacoes adicionais caso necessario
		return p != null;
	}

	public void editarPropriedadeVendedor(Vendedor vendedorAutenticado) {
		propriedadeView.solicitarIdParaConsulta();
		int id = propriedadeView.getNextInt();
		Propriedade propriedade = propriedadeDAO.get(id);
		if (propriedade.getVendedor() != vendedorAutenticado.getId()) {
			propriedadeView.modificarPropriedadeNaoAutorizado();
			return;
		}

		Propriedade propriedadeEditada = propriedadeView.editarPropriedadeCadastrada(propriedade);

		if (propriedadeDAO.update(id, propriedadeEditada))
			propriedadeView.atualizacaoSuccess();
	}
}
