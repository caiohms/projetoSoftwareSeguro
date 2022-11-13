package dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Usuario;
import model.Vendedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class VendedorDAO extends GenericDaoImpl<Vendedor> {

	public VendedorDAO() {
		super(Vendedor.class);
	}

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
	public List<Vendedor> getAll() {
		List<Vendedor> list = new ArrayList<>();
		String selectStr = "SELECT * FROM " + getTableName();
		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(DatabaseConverter.convertVendedor(rs));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public boolean save(Vendedor vendedor) throws SQLException {

		String password = vendedor.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());


		String insertString = "INSERT INTO " + getTableName() +
				"(nome,idade,sexo,cpf,email,password,telefone)" +
				" VALUES(?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			//Cria um PreparedStatment, classe usada para executar a query

			pstm.setString(1, vendedor.getNome());
			pstm.setString(2, vendedor.getIdade());
			pstm.setString(3, vendedor.getGenero());
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
		}

		return true;
	}


	@Override
	public Vendedor get(int id) {
		String selectString = "SELECT * FROM " + getTableName() + " WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(selectString)) {
			pstm.setInt(1, id);
			log.info("Getting Vendedor :: " + pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return DatabaseConverter.convertVendedor(rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	@Override
	public boolean update(int idToUpdate, Vendedor updatedVendedor) throws SQLException {
		String password = updatedVendedor.getPassword();
		String bcryptHashString = BCrypt.withDefaults().hashToString(6, password.toCharArray());


		String insertString = "UPDATE " + getTableName() + " " +
				"SET nome = ?, idade = ?, sexo = ?, cpf = ?, email = ?, password = ?, telefone = ? " +
				"WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			pstm.setString(1, updatedVendedor.getNome());
			pstm.setString(2, updatedVendedor.getIdade());
			pstm.setString(3, updatedVendedor.getGenero());
			pstm.setString(4, updatedVendedor.getCpf());
			pstm.setString(5, updatedVendedor.getEmail());
			pstm.setString(6, bcryptHashString);
			pstm.setString(7, updatedVendedor.getTelefone());
			pstm.setInt(8, idToUpdate);

			log.info("Atualizando dados do usuario :: " + pstm);

			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

}
