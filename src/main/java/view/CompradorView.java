package view;

import model.Comprador;
import model.Endereco;
import model.Vendedor;

import java.util.List;
import java.util.stream.Collectors;

public class CompradorView extends View {

	public Comprador realizarCadastro() {
		Comprador c = new Comprador();

		System.out.println("##   Cadastro do Comprador   ##");
		divisor();
		String email = lengthLimitedStringInput("| E-mail: ", 100);
		String senha = lengthLimitedStringInput("| Senha: ", 255);
		String nome = lengthLimitedStringInput("| Nome Completo: ", 50);
		String idade = lengthLimitedStringInput("| Idade: ", 2);
		String genero = lengthLimitedStringInput("| Genero: ", 1);
		String cpf = lengthLimitedStringInput("| CPF: ", 11);
		String telefone = lengthLimitedStringInput("| Telefone: ", 13);
		c.setEmail(email);
		c.setPassword(senha);
		c.setNome(nome);
		c.setIdade(idade);
		c.setGenero(genero);
		c.setCpf(cpf);
		c.setTelefone(telefone);
		return c;
	}

	public Comprador atualizaComprador(Comprador compradorAutenticado) {
		Comprador cModificado = new Comprador();

		System.out.println("##  Atualizar dados do Comprador  ##");
		divisor();
		System.out.println("| Nota: deixe em branco para não realizar alterações no campo.");
		cModificado.setEmail(getEditingStringValue("Email", compradorAutenticado.getEmail(), 100));
		cModificado.setPassword(getEditingPasswordValue("Senha", compradorAutenticado.getPassword(), 100));
		cModificado.setNome(getEditingStringValue("Nome Completo", compradorAutenticado.getNome(), 255));
		cModificado.setIdade(getEditingStringValue("Idade", compradorAutenticado.getIdade(), 2));
		cModificado.setGenero(getEditingStringValue("Genero", compradorAutenticado.getGenero(), 1));
		cModificado.setCpf(getEditingStringValue("CPF", compradorAutenticado.getCpf(), 11));
		cModificado.setTelefone(getEditingStringValue("Telefone", compradorAutenticado.getTelefone(), 13));
		// endereco

		return cModificado;
	}

	public void listarCompradores(List<Comprador> compradores) {

		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"nome", 20},
				new Object[]{"idade", 8},
				new Object[]{"genero", 6},
				new Object[]{"cpf", 14},
				new Object[]{"email", 40},
				new Object[]{"telefone", 20}
//				new Object[]{"endereco", 10}
		);

		List<List<?>> data = compradores
				.stream()
				.map(c -> List.of(
						c.getId(),
						c.getNome(),
						c.getIdade(),
						c.getGenero(),
						c.getCpf(),
						c.getEmail(),
						c.getTelefone()))
//				c.get
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}

	public void exibirMeusDados(Comprador comprador) {
		System.out.println("##   Meu perfil de comprador   ##");
		divisor();
		System.out.println("| Email:    " + comprador.getEmail());
		System.out.println("| Nome:     " + comprador.getNome());
		System.out.println("| Idade:    " + comprador.getIdade());
		System.out.println("| Genero:   " + comprador.getGenero());
		System.out.println("| Cpf:      " + comprador.getCpf());
		System.out.println("| Telefone: " + comprador.getTelefone());
//		System.out.println("| Endereco: " + comprador.getEndereco());
		divisor();
	}

	public String requestSearchString() {
		System.out.println("Digite a string de busca: ");
		return sc.nextLine();
	}

	public void showVendedor(Vendedor vendedor) {
		System.out.println("##   Dados do vendedor: " + vendedor.getId() + "   ##");
		divisor();
		System.out.println("| Nome:     " + vendedor.getNome());
		System.out.println("| Genero: " + vendedor.getGenero());
		System.out.println("| Telefone: " + vendedor.getTelefone());
		divisor();
	}

	public void listarVendedores(List<Vendedor> vendedores) {
		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"nome", 40}
		);

		List<List<?>> data = vendedores
				.stream()
				.map(v -> List.of(
						v.getId(),
						v.getNome()))
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}

	public Endereco realizarCadastroEndereco() {
		Endereco endereco = new Endereco();

		System.out.println("##   Cadastro de endereco   ##");
		System.out.print("| Logradouro: ");
		String logradouro = sc.nextLine();
		endereco.setLogradouro(logradouro);

		System.out.print("| Numero: ");
		String numero = sc.nextLine();
		endereco.setNumero(numero);

		System.out.print("| Complemento: ");
		String complemento = sc.nextLine();
		endereco.setComplemento(complemento);
		endereco.setFkCidade(1);

		return endereco;
	}
}
