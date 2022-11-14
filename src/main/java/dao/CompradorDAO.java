package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompradorDAO extends GenericDaoImpl<Comprador> {

	public CompradorDAO() {
		super(Comprador.class);
	}

	public Comprador getCompradorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		ResultSet rs;

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();

			if (rs.next()) {
				return DatabaseConverter.convertComprador(rs);
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		log.error("Usuario nao encontrado");
		return null;
	}

	@Override
	protected String setTableName() {
		return "comprador";
	}

	@Override
	public List<Comprador> getAll() {
		ResultSet rs;
		List<Comprador> list = new ArrayList<>();
		String selectStr = "SELECT * FROM " + getTableName();

		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(DatabaseConverter.convertComprador(rs));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public Comprador save(Comprador comprador) throws SQLException {
		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS)) {
			//Cria um PreparedStatment, classe usada para executar a query
			String password = comprador.getPassword();
			String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());

			pstm.setString(1, comprador.getNome());
			pstm.setString(2, comprador.getIdade());
			pstm.setString(3, comprador.getGenero());
			pstm.setString(4, comprador.getCpf());
			pstm.setString(5, comprador.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, comprador.getTelefone());

			log.info("Cadastrando usuario :: " + pstm);
			pstm.execute();
			ResultSet rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				comprador.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return comprador;
	}

	@Override
	public Comprador get(int id) {
		String selectString = "SELECT * FROM " + getTableName() + " WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(selectString)) {
			pstm.setInt(1, id);
			log.info("Getting Comprador :: " + pstm);
			ResultSet rs = pstm.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return DatabaseConverter.convertComprador(rs);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean update(int idToUpdate, Comprador updatedComprador) throws SQLException {

		String password = updatedComprador.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());

		String updateString = "UPDATE " + getTableName() +
				" SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ?, fk_endereco = ?" +
				" WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(updateString)) {
			pstm.setObject(1, updatedComprador.getNome());
			pstm.setObject(2, updatedComprador.getIdade());
			pstm.setObject(3, updatedComprador.getGenero());
			pstm.setObject(4, updatedComprador.getCpf());
			pstm.setObject(5, updatedComprador.getEmail());
			pstm.setObject(6, bcryptHashString);
			pstm.setObject(7, updatedComprador.getTelefone());
			pstm.setObject(8, updatedComprador.getEndereco());
			pstm.setObject(9, idToUpdate);

			log.info("Atualizando dados do usuario :: " + pstm);
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

}
