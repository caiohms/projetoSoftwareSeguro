package dao;

import database.DatabaseConSingleton;
import model.Comprador;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompradorDAO extends GenericDaoImpl<Comprador> {

	private static final Connection conn = DatabaseConSingleton.getConn();

	public Comprador getCompradorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM ? WHERE E_mail = ?";

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, getTableName());
			ps.setString(2, user.getEmail());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected String setTableName() {
		return "comprador";
	}

	@Override
	public int add(Comprador comprador) throws SQLException {
		//TODO
		return 0;
	}

	@Override
	public Comprador get(int id) throws SQLException {
		//TODO
		return null;
	}

	@Override
	public void update(Comprador comprador) throws SQLException {
		//TODO
	}

	@Override
	public void delete(int id) throws SQLException {
		//TODO
	}

}
