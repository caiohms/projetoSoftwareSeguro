package dao;

import database.DatabaseConSingleton;
import model.Comprador;
import model.Usuario;
import model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO<T> {

	private static final Connection conn = DatabaseConSingleton.getConn();

	private final Class<T> type;

	public AutenticacaoDAO(Class<T> type) {
		this.type = type;
	}

	public boolean autenticarUsuario(Usuario u) {

		String tableName;

		if (type == Comprador.class) {
			tableName = "comprador";
		} else if (type == Vendedor.class) {
			tableName = "vendedor";
		} else {
			tableName = "corretor";
		}

		String query = "SELECT * FROM " + tableName + " WHERE e_mail = ? AND senha = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
}
