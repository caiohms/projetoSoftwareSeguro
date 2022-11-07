package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompradorDAO extends GenericDaoImpl<Comprador> {

	private static final Connection conn = DatabaseConSingleton.getConn();

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
	public boolean save(Comprador comprador) throws SQLException {

		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query
			String password = comprador.getPassword();
			String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());

			pstm.setString(1, comprador.getNome());
			pstm.setString(2, comprador.getIdade());
			pstm.setString(3, comprador.getSexo());
			pstm.setString(4, comprador.getCpf());
			pstm.setString(5, comprador.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, comprador.getTelefone());

			log.info("Cadastrando usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	@Override
	public Comprador get(int id) {
		String selectString = "SELECT * FROM " + getTableName() + " WHERE id = ?";

		ResultSet rs;
		String email = null;
		String nome = null;
		String idade = null;
		String sexo = null;
		String cpf = null;
		String telefone = null;

		try (PreparedStatement pstm = conn.prepareStatement(selectString)) {
			pstm.setInt(1, id);
			log.info("Getting Comprador :: " + pstm);

			//Executa a sql para inserção dos dados
			rs = pstm.executeQuery();

			if (!rs.next()) {
				return null;
			}

			email = rs.getString("email");
			nome = rs.getString("nome");
			idade = rs.getString("idade");
			sexo = rs.getString("sexo");
			cpf = rs.getString("cpf");
			telefone = rs.getString("telefone");

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		// Criando um novo corretor com os dados encontrados na base de dados
		return new Comprador(email, id, nome, idade, sexo, cpf, telefone);
	}

	@Override
	public boolean update(Comprador comprador, int id) throws SQLException {

		String password = comprador.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());

		String insertString = "UPDATE " + getTableName() + " " +
				"SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ? " +
				"WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			pstm.setString(1, comprador.getNome());
			pstm.setString(2, comprador.getIdade());
			pstm.setString(3, comprador.getSexo());
			pstm.setString(4, comprador.getCpf());
			pstm.setString(5, comprador.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, comprador.getTelefone());
			pstm.setInt(8, id);

			log.info("Atualizando dados do usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}


	@Override
	public void delete(int id) throws SQLException {
		//TODO
		String deleteQuery = "DELETE FROM " + getTableName() + " WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(deleteQuery)) {
			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
