package dao.helper;

import lombok.extern.slf4j.Slf4j;
import model.Adm;
import model.Comprador;
import model.Corretor;
import model.Vendedor;

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
			converted.setSexo(resultSet.getString("sexo"));
			converted.setCpf(resultSet.getString("cpf"));
			converted.setTelefone(resultSet.getString("telefone"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert Comprador.");
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
			converted.setSexo(resultSet.getString("sexo"));
			converted.setCpf(resultSet.getString("cpf"));
			converted.setTelefone(resultSet.getString("telefone"));
			//		 TODO: convert endereco

		} catch (Exception e) {
			log.error("Failed to convert Comprador.");
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
			log.error("Failed to convert Corretor.");
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
			log.error("Failed to convert Administrator.");
			return null;
		}

		return converted;
	}

}
