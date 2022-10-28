package dao;

import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import model.Vendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class VendedorDAO extends GenericDaoImpl<Vendedor> {

	public Vendedor getVendedorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		ResultSet rs;

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

		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, vendedor.getNome());
			pstm.setString(2, vendedor.getIdade());
			pstm.setString(3, vendedor.getSexo());
			pstm.setString(4, vendedor.getCpf());
			pstm.setString(5, vendedor.getEmail());
			pstm.setString(6, vendedor.getPassword()); // TODO add bcrypt
			pstm.setString(7, vendedor.getTelefone());

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
	public Vendedor get(int id) {
		String selectString = "SELECT * FROM corretor WHERE id = ?";

		ResultSet rs = null;
		PreparedStatement pstm = null;

		String nome = null;
		String idade = null;
		String sexo = null;
		String cpf = null;
		String telefone = null;

		try {

			//Cria um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(selectString);

			pstm.setInt(1, id);
			log.info("Getting Vendedor :: " + pstm);

			//Executa a sql para inserção dos dados
			rs = pstm.executeQuery();

			nome = rs.getString("nome");
			idade = rs.getString("idade");
			sexo = rs.getString("sexo");
			cpf = rs.getString("cpf");
			telefone = rs.getString("telefone");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
//            return false;
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}


		// Criando um novo corretor com os dados encontrados na base de dados
		Vendedor vendedor = new Vendedor(id, nome, idade, sexo, cpf, telefone);

		return vendedor;
	}

	@Override
	public boolean update(Vendedor vendedor, int id) throws SQLException {

		String insertString = "UPDATE vendedor SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ?" +
				" WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, vendedor.getNome());
			pstm.setString(2, vendedor.getIdade());
			pstm.setString(3, vendedor.getSexo());
			pstm.setString(4, vendedor.getCpf());
			pstm.setString(5, vendedor.getEmail());
			pstm.setString(6, vendedor.getPassword()); // TODO add bcrypt
			pstm.setString(7, vendedor.getTelefone());
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
