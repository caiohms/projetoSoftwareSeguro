package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Corretor;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Slf4j
public class CorretorDAO extends GenericDaoImpl<Corretor> {

	public CorretorDAO() {
		super(Corretor.class);
	}

	public Corretor getCorretorFromUsuario(Usuario user) {
		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return DatabaseConverter.convertCorretor(rs);
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		log.error("Usuario nao encontrado");
		return null;
	}

	@Override
	protected String setTableName() {
		return "corretor";
	}

	@Override
	public List<Corretor> getAll() {
		return null;
	}

	@Override
	public Corretor save(Corretor corretor) throws SQLException {
		String password = corretor.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());

		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, corretor.getNome());
			pstm.setString(2, corretor.getIdade());
			pstm.setString(3, corretor.getSexo());
			pstm.setString(4, corretor.getCpf());
			pstm.setString(5, corretor.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, corretor.getTelefone());

			log.info("Cadastrando usuario :: " + pstm);
			pstm.execute();
			ResultSet rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				corretor.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return corretor;
	}

	@Override
	public Corretor get(int id) {
		String selectString = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(selectString)) {
			pstm.setInt(1, id);
			log.info("Getting Corretor :: " + pstm);

			//Executa a sql para inserção dos dados
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return DatabaseConverter.convertCorretor(rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean update(int idToUpdate, Corretor updatedCorretor) throws SQLException {

		String insertString = "UPDATE " + getTableName() + " " +
				"SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ? " +
				"WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, updatedCorretor.getNome());
			pstm.setString(2, updatedCorretor.getIdade());
			pstm.setString(3, updatedCorretor.getSexo());
			pstm.setString(4, updatedCorretor.getCpf());
			pstm.setString(5, updatedCorretor.getEmail());

			String password = updatedCorretor.getPassword();
			String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, updatedCorretor.getTelefone());
			pstm.setInt(8, idToUpdate);

			log.info("Atualizando dados do usuario :: " + pstm);
			pstm.execute();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
		return true;
	}
}
