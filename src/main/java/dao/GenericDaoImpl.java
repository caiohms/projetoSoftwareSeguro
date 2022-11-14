package dao;

import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

	protected static final Connection conn = DatabaseConSingleton.getConn();
	private final String tableName;
	private final Class<T> type;

	protected GenericDaoImpl(Class<T> type) {
		this.type = type;
		tableName = setTableName();
	}

	protected abstract String setTableName();

	public String getTableName() {
		return tableName;
	}

	public boolean checkTakenEmail(String email) {
		ResultSet rs;
		String selectStr = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) return true;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

	public List<T> getAll() {
		return Collections.emptyList();
	};

	@Override
	public void delete(int id) throws SQLException {
		String deleteQuery = "DELETE FROM " + getTableName() + " WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(deleteQuery)) {
			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}