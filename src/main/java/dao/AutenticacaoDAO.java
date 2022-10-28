package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AutenticacaoDAO<T1 extends GenericDaoImpl<?>> {

	private static final Connection conn = DatabaseConSingleton.getConn();

	private final Class<T1> daoType;

	public AutenticacaoDAO(Class<T1> daoType) {
		this.daoType = daoType;
	}

	public boolean autenticarUsuario(Usuario u) {

		log.debug("Autenticando usuario...");

		String tableName;
		String password = u.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

		try {
			tableName = daoType.getDeclaredConstructor().newInstance().getTableName();
		} catch (Exception e) {
			e.printStackTrace();
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
