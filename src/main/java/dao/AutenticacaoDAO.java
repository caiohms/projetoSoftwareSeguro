package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;
import model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AutenticacaoDAO<T> {

	private static final Connection conn = DatabaseConSingleton.getConn();

	private final Class<T> type;

	public AutenticacaoDAO(Class<T> type) {
		this.type = type;
	}

	public boolean autenticarUsuario(Usuario u) {

		log.debug("Autenticando usuario...");

		String tableName;
		String password = u.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

		if (type == Comprador.class) {
			tableName = "comprador";
		} else if (type == Vendedor.class) {
			tableName = "vendedor";
		} else {
			tableName = "corretor";
		}

		String query = "SELECT * FROM " + tableName + " WHERE email = ? AND password = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, u.getEmail());
			ps.setString(2, bcryptHashString);

			log.debug(ps.toString());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
}
