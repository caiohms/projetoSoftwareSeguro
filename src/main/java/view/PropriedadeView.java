package view;

import model.Propriedade;

public class PropriedadeView extends View {

	public Propriedade cadastrar() {
		Propriedade p = new Propriedade();

		System.out.println("##-- Cadastro de Propriedade --##");
		System.out.println("|-------------------------------|");

		String descricao = lengthLimitedStringInput("| Descricao: ", 1024);
		Double aTotal = validatedDoubleInput("| Metragem total (m2): ");
		Double aUtil = validatedDoubleInput("| Area util(m2): ");
		Integer quartos = validatedIntegerInput("| Quartos: ");
		Integer banheiros = validatedIntegerInput("| Banheiros: ");
		Integer vagasGaragem = validatedIntegerInput("| Vagas de garagem: ");
		Double preco = validatedDoubleInput("| Preco: ");
		Double valorCond = validatedDoubleInput("| Valor condominio: ");
//		Integer tipo = validatedIntegerInput("| Tipo: ");
		String logradouro = lengthLimitedStringInput("| Logradouro: ", 255);
		String numero = lengthLimitedStringInput("| Numero: ", 10);
		String compl = lengthLimitedStringInput("| Complemento: ", 255);
		String cep = lengthLimitedStringInput("| CEP (somente numeros): ", 8);
		String bairro = lengthLimitedStringInput("| Bairro: ", 30);
//		String estado = lengthLimitedStringInput("| Estado (sigla): ", 2);

		p.setDescricao(descricao);
		p.setATotal(aTotal);
		p.setAUtil(aUtil);
		p.setQuartos(quartos);
		p.setBanheiros(banheiros);
		p.setVagasGaragem(vagasGaragem);
		p.setPreco(preco);
		p.setValorCond(valorCond);
		p.setTipo(1); // a venda
		p.setLogradouro(logradouro);
		p.setNumero(numero);
		p.setComplemento(compl);
		p.setCep(cep);
		p.setBairro(bairro);
		p.setEstado(1); // PR

		return p;
	}

	public void cadastroSuccess() {
		System.out.println("Propriedade cadastrada com sucesso.");
	}
}
