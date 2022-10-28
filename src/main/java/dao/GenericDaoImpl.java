package dao;

import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public abstract class GenericDaoImpl<T> implements GenericDAO<T> {

	protected static final Connection conn = DatabaseConSingleton.getConn();

	private final String tableName;

	protected GenericDaoImpl() {
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
}