package dao;

import lombok.extern.slf4j.Slf4j;
import model.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class EnderecoDAO extends GenericDaoImpl<Endereco> {

	public EnderecoDAO() {
		super(Endereco.class);
	}

	@Override
	protected String setTableName() {
		return "endereco";
	}

	@Override
	public Endereco save(Endereco endereco) throws SQLException {
		String insertString = "INSERT INTO " + getTableName() +
				"(id,logradouro,numero,complemento,id_Cidade)" +
				" VALUES(?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setObject(1, null);
			pstm.setObject(2, endereco.getLogradouro());
			pstm.setObject(3, endereco.getNumero());
			pstm.setObject(4, endereco.getComplemento());
			pstm.setObject(5, endereco.getFkCidade());

			log.info("Cadastrando usuario :: " + pstm);
			pstm.execute();
			ResultSet rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				endereco.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
		return endereco;
	}

	@Override
	public Endereco get(int id) throws SQLException {
		return null;
	}

	@Override
	public boolean update(int idToUpdate, Endereco endereco) throws SQLException {
		return false;
	}
}
