package view;

import model.Propriedade;
import model.Vendedor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VendedorView extends View {

	public Vendedor realizarCadastro() {
		Vendedor novoVendedor = new Vendedor();

		System.out.println("##   Cadastro do Vendedor   ##");
		System.out.println("|-----------------------------");

		System.out.print("| E-mail: ");
		novoVendedor.setEmail(sc.nextLine());

		System.out.print("| Senha: ");
		novoVendedor.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: ");
		novoVendedor.setNome(sc.nextLine());

		System.out.print("| Idade: ");
		novoVendedor.setIdade(sc.nextLine());

		System.out.print("| Sexo: ");
		novoVendedor.setGenero(sc.nextLine());

		System.out.print("| CPF: ");
		novoVendedor.setCpf(sc.nextLine());

		System.out.print("| Telefone: ");
		novoVendedor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return novoVendedor;
	}

	public Vendedor atualizarVendedor() {
		Vendedor vendedor = new Vendedor();

		System.out.println("\n##--Atualizar dados do Comprador--##\n");
		System.out.println("|-----------------------------|");

		System.out.print("| E-mail: \n");
		vendedor.setEmail(sc.nextLine());

		System.out.print("| Senha: \n");
		vendedor.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: \n");
		vendedor.setNome(sc.nextLine());

		System.out.print("| Idade: \n");
		vendedor.setIdade(sc.nextLine());

		System.out.print("| Sexo: \n");
		vendedor.setGenero(sc.nextLine());

		System.out.print("| CPF: \n");
		vendedor.setCpf(sc.nextLine());

		System.out.print("| Telefone: \n");
		vendedor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return vendedor;
	}

	public void exibirDados(Vendedor vendedorAutenticado) {

		System.out.println("\n##-- Dados do vendedor autenticado --##\n");
		System.out.println("|-----------------------------|");
		System.out.println("| E-mail: " + vendedorAutenticado.getEmail());
		System.out.println("| Nome Completo: " + vendedorAutenticado.getNome());
		System.out.println("| Idade: " + vendedorAutenticado.getIdade());
		System.out.println("| Sexo: " + vendedorAutenticado.getGenero());
		System.out.println("| CPF: " + vendedorAutenticado.getCpf());
		System.out.println("| Telefone: " + vendedorAutenticado.getTelefone());
	}

	public void listarVendedores(List<Vendedor> vendedores) {

		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"nome", 40},
				new Object[]{"idade", 8},
				new Object[]{"genero", 10},
				new Object[]{"telefone", 15}
		);

		List<List<?>> data = vendedores
				.stream()
				.map(v -> List.of(
						v.getId(),
						v.getNome(),
						v.getIdade(),
						v.getGenero(),
						v.getTelefone()))
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}

	public Propriedade editarPropriedadeCadastrada(Propriedade propriedadeOriginal) {

		Propriedade pModificada = new Propriedade();

		System.out.println("\n##    Atualizar proprieadade cadastrada    ##\n");
		System.out.println("|-----------------------------");

		System.out.println("| Tipos disponiveis: ");
		System.out.println("|    (1) Casa residencial ");
		System.out.println("|    (2) Apartamento ");
		System.out.println("|    (3) Terreno ");
		System.out.println("|    (4) Comercial ");
		System.out.println("|    (5) Industrial ");
		pModificada.setTipo(getEditingLimitedIntegerOption("Tipo", propriedadeOriginal.getTipo(), 1, 5));

		pModificada.setDescricao(getEditingStringValue("Descricao", propriedadeOriginal.getDescricao(), 1024));
		pModificada.setATotal(getEditingDoubleValue("Area total", propriedadeOriginal.getATotal()));
		pModificada.setAUtil(getEditingDoubleValue("Area util", propriedadeOriginal.getAUtil()));
		pModificada.setQuartos(getEditingIntegerOption("Quartos", propriedadeOriginal.getQuartos()));
		pModificada.setBanheiros(getEditingIntegerOption("Banheiros", propriedadeOriginal.getBanheiros()));
		pModificada.setVagasGaragem(getEditingIntegerOption("Vagas garagem", propriedadeOriginal.getVagasGaragem()));
		pModificada.setPreco(getEditingDoubleValue("Preco", propriedadeOriginal.getPreco()));
		pModificada.setValorCond(getEditingDoubleValue("Valor condominio", propriedadeOriginal.getValorCond()));
		pModificada.setLogradouro(getEditingStringValue("Logradouro", propriedadeOriginal.getLogradouro(), 255));
		pModificada.setNumero(getEditingStringValue("Numero", propriedadeOriginal.getNumero(), 10));
		pModificada.setComplemento(getEditingStringValue("Complemento", propriedadeOriginal.getComplemento(), 255));
		pModificada.setCep(getEditingStringValue("CEP", propriedadeOriginal.getCep(), 8));
		pModificada.setBairro(getEditingStringValue("Bairro", propriedadeOriginal.getBairro(), 30));
		pModificada.setUpdatedAt(new Date());
		return pModificada;
	}
}
