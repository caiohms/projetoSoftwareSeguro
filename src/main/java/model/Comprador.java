package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Comprador extends Usuario implements Serializable {

	private int id;
	private String nome;
	private String idade;
	private String sexo;
	private String cpf;
	private String telefone;
//	private String idEndereco;

	public Comprador() { /* empty constructor */ }

	public Comprador(String email, int id, String nome, String idade, String sexo, String cpf, String telefone) {
		this.setEmail(email);
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
	}
}

