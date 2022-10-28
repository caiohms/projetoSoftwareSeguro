package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class VendedorDAO extends GenericDaoImpl<Vendedor> {
	private static final Connection conn = DatabaseConSingleton.getConn();

	public Vendedor getVendedorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		ResultSet rs = null;

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();

			if (rs.next()) {
				return DatabaseConverter.convertVendedor(rs);
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}

		log.error("Usuario nao encontrado");
		return null;
	}

	@Override
	protected String setTableName() {
		return "vendedor";
	}

	@Override
	public boolean save(Vendedor vendedor) throws SQLException {

		String password = vendedor.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());


		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		PreparedStatement pstm = null;

		try {
			//Cria um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(insertString);

			pstm.setString(1, vendedor.getNome());
			pstm.setString(2, vendedor.getIdade());
			pstm.setString(3, vendedor.getSexo());
			pstm.setString(4, vendedor.getCpf());
			pstm.setString(5, vendedor.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, vendedor.getTelefone());

			log.info("Cadastrando usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		} finally {
			if (pstm != null)
				pstm.close();
		}

		return true;
	}


	@Override
	public Vendedor get(int id) throws SQLException {
		//TODO
		return null;
	}

	@Override
	public boolean update(Vendedor vendedor, int id) throws SQLException {

		String password = vendedor.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());


		String insertString = "UPDATE vendedor SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ?" +
				" WHERE id = ?";

		PreparedStatement pstm = null;

		try {
			//Cria um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(insertString);

			pstm.setString(1, vendedor.getNome());
			pstm.setString(2, vendedor.getIdade());
			pstm.setString(3, vendedor.getSexo());
			pstm.setString(4, vendedor.getCpf());
			pstm.setString(5, vendedor.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, vendedor.getTelefone());
			pstm.setInt(8, id);

			log.info("Atualizando dados do usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		} finally {
			if (pstm != null)
				pstm.close();
		}

		return true;
	}

	@Override
	public void delete(int id) throws SQLException {
		//TODO
		String deleteQuery = "DELETE FROM vendedor WHERE id = ?";

		PreparedStatement pstm = null;

		try {
			pstm = conn.prepareStatement(deleteQuery);
			pstm.setInt(1, id);
			pstm.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
