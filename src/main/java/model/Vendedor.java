package model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Vendedor extends Usuario implements Serializable {
	private int id;
	private String nome;
	private String idade;
	private String sexo;
	private String cpf;
	private String telefone;

//	private String idEndereco;

	public Vendedor() { /* empty constructor */ }

	public Vendedor(int id, String nome, String idade, String sexo, String cpf, String telefone) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
	}
}
