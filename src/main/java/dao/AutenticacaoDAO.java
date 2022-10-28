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

		String tableName = null;
		String password = u.getPassword();

		try {
			tableName = daoType.getDeclaredConstructor().newInstance().getTableName();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String query = "SELECT * FROM " + tableName + " WHERE email = ?";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, u.getEmail());

			log.debug(ps.toString());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), rs.getString("password"));
				return result.verified;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return false;
	}
}
