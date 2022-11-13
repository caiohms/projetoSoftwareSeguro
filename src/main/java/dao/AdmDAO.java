package dao;

import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Adm;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class AdmDAO extends GenericDaoImpl<Adm> {

	public AdmDAO() {
		super(Adm.class);
	}

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

	@Override
	public List<Adm> getAll() {
		return Collections.emptyList();
	}

	public boolean save(Adm adm) throws SQLException {
		// Admins cannot be added
		return false;
	}

	public Adm get(int id) {
		// Admins cannot be queried
		return null;
	}

	@Override
	public boolean update(int idToUpdate, Adm adm) {
		// Admins cannot be updated
		return false;
	}

	@Override
	public void delete(int id) {
		// Admins cannot be deleted
	}

}
