package dao.helper;

import lombok.extern.slf4j.Slf4j;
import model.*;

import java.sql.ResultSet;

@Slf4j
public class DatabaseConverter {

	private DatabaseConverter() {
	}

	public static Comprador convertComprador(ResultSet resultSet) {
		Comprador converted = new Comprador();

		try {
			converted.setId(resultSet.getInt("id"));
			converted.setEmail(resultSet.getString("email"));
			converted.setPassword(resultSet.getString("password"));
			converted.setNome(resultSet.getString("nome"));
			converted.setIdade(resultSet.getString("idade"));
			converted.setGenero(resultSet.getString("sexo"));
			converted.setCpf(resultSet.getString("cpf"));
			converted.setTelefone(resultSet.getString("telefone"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert.", e);
			return null;
		}

		return converted;
	}

	public static Vendedor convertVendedor(ResultSet resultSet) {
		Vendedor converted = new Vendedor();

		try {
			converted.setId(resultSet.getInt("id"));
			converted.setEmail(resultSet.getString("email"));
			converted.setPassword(resultSet.getString("password"));
			converted.setNome(resultSet.getString("nome"));
			converted.setIdade(resultSet.getString("idade"));
			converted.setGenero(resultSet.getString("sexo"));
			converted.setCpf(resultSet.getString("cpf"));
			converted.setTelefone(resultSet.getString("telefone"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert.", e);
			return null;
		}

		return converted;
	}

	public static Corretor convertCorretor(ResultSet resultSet) {
		Corretor converted = new Corretor();

		try {
			converted.setId(resultSet.getInt("id"));
			converted.setEmail(resultSet.getString("email"));
			converted.setPassword(resultSet.getString("password"));
			converted.setNome(resultSet.getString("nome"));
			converted.setIdade(resultSet.getString("idade"));
			converted.setSexo(resultSet.getString("sexo"));
			converted.setCpf(resultSet.getString("cpf"));
			converted.setTelefone(resultSet.getString("telefone"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert.", e);
			return null;
		}

		return converted;
	}

	public static Adm convertAdm(ResultSet resultSet) {
		Adm converted = new Adm();

		try {
			converted.setId(resultSet.getInt("id"));
			converted.setEmail(resultSet.getString("email"));
			converted.setPassword(resultSet.getString("password"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert.", e);
			return null;
		}

		return converted;
	}

	public static Propriedade convertPropriedade(ResultSet resultSet) {

		Propriedade converted = new Propriedade();

		try {
			converted.setId(resultSet.getInt("id"));
			converted.setVendedor(resultSet.getInt("fk_vendedor"));
			converted.setCorretor(resultSet.getInt("fk_corretor"));
			converted.setStatus(resultSet.getInt("fk_status"));
			converted.setTipo(resultSet.getInt("fk_tipo"));
			converted.setDescricao(resultSet.getString("descricao"));
			converted.setATotal(resultSet.getDouble("area_total"));
			converted.setAUtil(resultSet.getDouble("area_util"));
			converted.setQuartos(resultSet.getInt("quartos"));
			converted.setBanheiros(resultSet.getInt("banheiros"));
			converted.setVagasGaragem(resultSet.getInt("vagas_garagem"));
			converted.setPreco(resultSet.getDouble("preco"));
			converted.setValorCond(resultSet.getDouble("valor_cond"));
			converted.setLogradouro(resultSet.getString("logradouro"));
			converted.setNumero(resultSet.getString("numero"));
			converted.setComplemento(resultSet.getString("complemento"));
			converted.setCep(resultSet.getString("cep"));
			converted.setBairro(resultSet.getString("bairro"));
			converted.setEstado(resultSet.getInt("uf"));
			converted.setCreatedAt(resultSet.getDate("created_at"));
			converted.setUpdatedAt(resultSet.getDate("updated_at"));
		} catch (Exception e) {
			log.error("Failed to convert.", e);
			return null;
		}

		return converted;
	}
}
