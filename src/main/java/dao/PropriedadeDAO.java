package dao;

import dao.helper.DatabaseConverter;
import lombok.extern.slf4j.Slf4j;
import model.Propriedade;
import model.Vendedor;

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

	public static List<Propriedade> getPropriedadesOfVendedor(Vendedor vendedor) {
		//TODO
		return List.of();
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
	public Propriedade get(int id)  {
		//TODO
		return null;
	}

	@Override
	public boolean update(Propriedade propriedade, int id) throws SQLException {
		//TODO
		return false;
	}

	@Override
	public void delete(int id) throws SQLException {
		//TODO
		return;
	}

	@Override
	protected String setTableName() {
		return "propriedade";
	}

	@Override
	public List<Propriedade> getAll() {
		ResultSet rs;

		List<Propriedade> list = new ArrayList<>();

		String selectStr = "SELECT * FROM " + getTableName();

		try (PreparedStatement ps = conn.prepareStatement(selectStr)) {
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(DatabaseConverter.convertPropriedade(rs));
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
}
