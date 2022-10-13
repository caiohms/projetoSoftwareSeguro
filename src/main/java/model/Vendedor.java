package model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class Vendedor extends Usuario {
	private int id;
	private String nome;
	private String idade;
	private String sexo;
	private String cpf;
	private String telefone;

//	private String idEndereco;

	public Vendedor() { /* empty constructor */ }
}
