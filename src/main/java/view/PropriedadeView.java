package view;

import model.Propriedade;

import java.util.List;
import java.util.stream.Collectors;

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

	public void listarPropriedades(List<Propriedade> propriedades) {

		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"vendedor", 10},
				new Object[]{"corretor", 10},
				new Object[]{"status", 10},
				new Object[]{"tipo", 6},
				new Object[]{"descricao", 40},
				new Object[]{"a. total", 10},
				new Object[]{"a. util", 10},
				new Object[]{"quartos", 10},
				new Object[]{"banheiros", 10},
				new Object[]{"vagas", 10},
				new Object[]{"preco", 10},
				new Object[]{"valor cond.", 10},
				new Object[]{"logradouro", 30},
				new Object[]{"numero", 10},
				new Object[]{"compl", 10},
				new Object[]{"cep", 10},
				new Object[]{"bairro", 15},
				new Object[]{"uf", 5},
				new Object[]{"created", 11},
				new Object[]{"updated", 11}
		);

		List<List<?>> data = propriedades
				.stream()
				.map(p -> List.of(
						p.getId(),
						p.getVendedor(),
						p.getCorretor(),
						p.getStatus(),
						p.getTipo(),
						p.getDescricao(),
						p.getATotal(),
						p.getAUtil(),
						p.getQuartos(),
						p.getBanheiros(),
						p.getVagasGaragem(),
						p.getPreco(),
						p.getValorCond(),
						p.getLogradouro(),
						p.getNumero(),
						p.getComplemento(),
						p.getCep(),
						p.getBairro(),
						p.getEstado(),
						p.getCreatedAt(),
						p.getUpdatedAt()))
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}
}
