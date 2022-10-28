package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Adm;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AdmDAO extends GenericDaoImpl<Adm> {

	public Adm getAdmFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		ResultSet rs;

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();

			if (rs.next()) {
				return DatabaseConverter.convertAdm(rs);
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		log.error("Usuario nao encontrado");
		return null;
	}

	@Override
	protected String setTableName() {
		return "adm";
	}

	public boolean save(Adm adm) throws SQLException {

		String password = adm.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

		String insertString = "INSERT INTO " + getTableName() +
				"(email,password)" +
				" VALUES(?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, adm.getEmail());
			pstm.setString(2, adm.getPassword()); // TODO add bcrypt

			log.info("Cadastrando usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();


		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	public Adm get(int id) {
		// Admins cannot be queried
		return null;
	}

	@Override
	public boolean update(Adm adm, int id) {
		// Admins cannot be updated
		return false;
	}


	@Override
	public void delete(int id) throws SQLException {
		// Admins cannot be deleted
	}

}