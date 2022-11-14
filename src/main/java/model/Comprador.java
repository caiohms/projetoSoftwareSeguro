package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Comprador extends Usuario implements Serializable {
	private Integer id;
	private String nome;
	private String idade;
	private String genero;
	private String cpf;
	private String telefone;
	private Integer endereco;
}

