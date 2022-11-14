package model;

import lombok.Data;

@Data
public class Endereco {
	private Integer id;
	private String logradouro;
	private String numero;
	private String complemento;
	private Integer fkCidade;
}
