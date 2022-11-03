package model;

import lombok.Data;

@Data
public class Propriedade {

	private Integer id;
	private Integer vendedor;
	private Integer corretor;
	private Integer status;
	private Integer tipo;
	private String descricao;
	private Double aTotal;
	private Double aUtil;
	private Integer quartos;
	private Integer banheiros;
	private Integer vagasGaragem;
	private Double preco;
	private Double valorCond;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private Integer estado;

}
