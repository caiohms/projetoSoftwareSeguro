package dao;

import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Propriedade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class PropriedadeDAO extends GenericDaoImpl<Propriedade> {

	public PropriedadeDAO() {
		super(Propriedade.class);
	}

	public List<Propriedade> getPropriedadesOfVendedor(int vendedorId) {
		ResultSet rs;
		List<Propriedade> list = new ArrayList<>();
		String selectStr = "SELECT * FROM " + getTableName() + " WHERE fk_vendedor = ? ORDER BY id";

		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			ps.setInt(1, vendedorId);

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(DatabaseConverter.convertPropriedade(rs));
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public boolean save(Propriedade propriedade) throws SQLException {
		String insertString = "INSERT INTO " + getTableName() +
				"(id,fk_Vendedor,fk_Corretor,fk_status,fk_tipo,descricao,area_total,area_util,quartos,banheiros,vagas_garagem,preco,valor_cond,logradouro,numero,complemento,cep,bairro,uf,created_at,updated_at)" + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(insertString)) {
			pstm.setObject(1, null);
			pstm.setObject(2, propriedade.getVendedor());
			pstm.setObject(3, propriedade.getCorretor());
			pstm.setObject(4, propriedade.getStatus());
			pstm.setObject(5, propriedade.getTipo());
			pstm.setObject(6, propriedade.getDescricao());
			pstm.setObject(7, propriedade.getATotal());
			pstm.setObject(8, propriedade.getAUtil());
			pstm.setObject(9, propriedade.getQuartos());
			pstm.setObject(10, propriedade.getBanheiros());
			pstm.setObject(11, propriedade.getVagasGaragem());
			pstm.setObject(12, propriedade.getPreco());
			pstm.setObject(13, propriedade.getValorCond());
			pstm.setObject(14, propriedade.getLogradouro());
			pstm.setObject(15, propriedade.getNumero());
			pstm.setObject(16, propriedade.getComplemento());
			pstm.setObject(17, propriedade.getCep());
			pstm.setObject(18, propriedade.getBairro());
			pstm.setObject(19, propriedade.getEstado());
			pstm.setObject(20, new Date());
			pstm.setObject(21, new Date());

			log.info("Persistindo propriedade :: " + pstm);

			pstm.execute();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	@Override
	public Propriedade get(int id) {
		String selectString = "SELECT * FROM " + getTableName() + " WHERE id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(selectString)) {
			pstm.setInt(1, id);
			log.info("Getting Propriedade :: " + pstm);

			ResultSet rs = pstm.executeQuery();
			if (!rs.next()) {
				return null;
			}
			return DatabaseConverter.convertPropriedade(rs);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public boolean update(int idToUpdate, Propriedade updatedPropriedade) {
		String updateString = "UPDATE " + getTableName() +
				" SET " +
				"fk_tipo=?," +
				"descricao=?," +
				"area_total=?," +
				"area_util=?," +
				"quartos=?," +
				"banheiros=?," +
				"vagas_garagem=?," +
				"preco=?," +
				"valor_cond=?," +
				"logradouro=?," +
				"numero=?," +
				"complemento=?," +
				"cep=?," +
				"bairro=?," +
				"updated_at=?" +
				" WHERE id = ?";

		try (PreparedStatement pstm = conn.prepareStatement(updateString)) {
			pstm.setObject(1, updatedPropriedade.getTipo());
			pstm.setObject(2, updatedPropriedade.getDescricao());
			pstm.setObject(3, updatedPropriedade.getATotal());
			pstm.setObject(4, updatedPropriedade.getAUtil());
			pstm.setObject(5, updatedPropriedade.getQuartos());
			pstm.setObject(6, updatedPropriedade.getBanheiros());
			pstm.setObject(7, updatedPropriedade.getVagasGaragem());
			pstm.setObject(8, updatedPropriedade.getPreco());
			pstm.setObject(9, updatedPropriedade.getValorCond());
			pstm.setObject(10, updatedPropriedade.getLogradouro());
			pstm.setObject(11, updatedPropriedade.getNumero());
			pstm.setObject(12, updatedPropriedade.getComplemento());
			pstm.setObject(13, updatedPropriedade.getCep());
			pstm.setObject(14, updatedPropriedade.getBairro());
			pstm.setObject(15, updatedPropriedade.getUpdatedAt());
			pstm.setInt(16, idToUpdate);

			log.info("Atualizando propriedade :: " + pstm);
			pstm.execute();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}

		return true;
	}

	@Override
	protected String setTableName() {
		return "propriedade";
	}

	@Override
	public List<Propriedade> getAll() {
		List<Propriedade> list = new ArrayList<>();
		String selectStr = "SELECT * FROM " + getTableName();
		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(DatabaseConverter.convertPropriedade(rs));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
}
