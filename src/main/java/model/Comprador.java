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

}

