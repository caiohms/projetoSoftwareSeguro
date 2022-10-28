package dao;

import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Corretor;
import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class CorretorDAO extends GenericDaoImpl<Corretor> {

	public Corretor getCorretorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

		ResultSet rs;

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();

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
	public boolean save(Corretor corretor) throws SQLException {

		String insertString = "INSERT INTO " + getTableName() + "(nome,idade,sexo,cpf,email,password,telefone)" + " VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, corretor.getNome());
			pstm.setString(2, corretor.getIdade());
			pstm.setString(3, corretor.getSexo());
			pstm.setString(4, corretor.getCpf());
			pstm.setString(5, corretor.getEmail());
			pstm.setString(6, corretor.getPassword()); // TODO add bcrypt
			pstm.setString(7, corretor.getTelefone());

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
	public Corretor get(int id) throws SQLException {
		//TODO
		return null;
	}

	@Override
	public boolean update(Corretor corretor, int id) throws SQLException {

		String insertString = "UPDATE corretor SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ?" + " WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, corretor.getNome());
			pstm.setString(2, corretor.getIdade());
			pstm.setString(3, corretor.getSexo());
			pstm.setString(4, corretor.getCpf());
			pstm.setString(5, corretor.getEmail());
			pstm.setString(6, corretor.getPassword()); // TODO add bcrypt
			pstm.setString(7, corretor.getTelefone());
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
		String deleteQuery = "DELETE FROM corretor WHERE id = ?";

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
