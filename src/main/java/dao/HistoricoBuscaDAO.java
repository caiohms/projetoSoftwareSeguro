package dao;

import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.HistoricoBusca;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Slf4j
public class HistoricoBuscaDAO extends GenericDaoImpl<HistoricoBusca> {

	public HistoricoBuscaDAO() {
		super(HistoricoBusca.class);
	}

	public List<HistoricoBusca> getHistoricoDeComprador(Comprador comprador) {
		//todo
		return null;
	}

	public void registrarBusca(Comprador compradorAutenticado, String searchString) {
		HistoricoBusca h = new HistoricoBusca(null, compradorAutenticado.getId(),searchString,new Date());
		this.save(h);
	}

	@Override
	public boolean save(HistoricoBusca historicoBusca) {
		String insertString = "INSERT INTO " + getTableName() +
				"(id,fk_comprador,string_busca,data)" +
				" VALUES(?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			pstm.setObject(1, historicoBusca.getId());
			pstm.setObject(2, historicoBusca.getComprador());
			pstm.setObject(3, historicoBusca.getBusca());
			pstm.setObject(4, historicoBusca.getDatetime());
			log.info("Registrando busca :: " + pstm);
			pstm.execute();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}

	@Override
	public HistoricoBusca get(int id) throws SQLException {
		return null;
	}

	@Override
	public boolean update(int idToUpdate, HistoricoBusca historicoBusca) throws SQLException {
		// Não é possível alterar registros.
		return false;
	}

	@Override
	protected String setTableName() {
		return "historico_busca_comprador";
	}

	@Override
	public List<HistoricoBusca> getAll() {
		return null;
	}
}
