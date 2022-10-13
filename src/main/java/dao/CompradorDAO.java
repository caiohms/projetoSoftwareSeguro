package dao;

import database.DatabaseConSingleton;
import model.Comprador;
import model.Usuario;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompradorDAO extends GenericDaoImpl<Comprador> {

	private static final Connection conn = DatabaseConSingleton.getConn();

	public Comprador getCompradorFromUsuario(Usuario user) {

		String selectionString = "SELECT * FROM ? WHERE E_mail = ?";

		try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
			ps.setString(1, getTableName());
			ps.setString(2, user.getEmail());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected String setTableName() {
		return "comprador";
	}

	@Override
	public int add(Comprador comprador) throws SQLException {
		//TODO
		String addComprador = "INSERT INTO comprador(nome,idade,sexo,cpf,email,senha,telefone,endereco)" +
				" VALUES(?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			//Cria uma conexão com o banco
			conn = DatabaseConSingleton.getConn();

			//Cria um PreparedStatment, classe usada para executar a query
			pstm = conn.prepareStatement(addComprador);

			//Adiciona o valor do primeiro parâmetro da sql
			pstm.setString(1, comprador.getNome());
			//Adicionar o valor do segundo parâmetro da sql
			pstm.setString(2, comprador.getIdade());
			//Adiciona o valor do terceiro parâmetro da sql
			pstm.setString(3, comprador.getSexo());
			//Adiciona o valor do quarto parâmetro da sql
			pstm.setString(4, comprador.getCpf());
			//Adicionar o valor do quinto parâmetro da sql
			pstm.setString(5, comprador.getEmail());
			//Adiciona o valor do sexo parâmetro da sql
			pstm.setString(6, comprador.getPassword());
			//Adiciona o valor do setimo parâmetro da sql
			pstm.setString(7, comprador.getEmail());
			//Adiciona o valor do oitavo parâmetro da sql
			pstm.setString(8, comprador.getPassword());


			//Executa a sql para inserção dos dados
			pstm.execute();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return 1;
	}


	@Override
	public Comprador get(int id) throws SQLException {
		//TODO
		return null;
	}

	@Override
	public void update(Comprador comprador) throws SQLException {
		//TODO
	}

	@Override
	public void delete(int id) throws SQLException {
		//TODO
	}

}
