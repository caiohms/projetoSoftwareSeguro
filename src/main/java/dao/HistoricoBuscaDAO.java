package dao;

import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.HistoricoBusca;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		HistoricoBusca h = new HistoricoBusca();
		h.setId(null);
		h.setComprador(compradorAutenticado.getId());
		h.setBusca(searchString);
		h.setDatetime(new Date());
		this.save(h);
	}

	@Override
	public HistoricoBusca save(HistoricoBusca historicoBusca) {
		String insertString = "INSERT INTO " + getTableName() +
				"(id,fk_comprador,string_busca,data)" +
				" VALUES(?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setObject(1, historicoBusca.getId());
			pstm.setObject(2, historicoBusca.getComprador());
			pstm.setObject(3, historicoBusca.getBusca());
			pstm.setObject(4, historicoBusca.getDatetime());
			log.info("Registrando busca :: " + pstm);
			pstm.execute();
			ResultSet rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				historicoBusca.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return historicoBusca;
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
