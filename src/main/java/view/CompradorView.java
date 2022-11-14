package view;

import model.Comprador;

import java.util.List;
import java.util.stream.Collectors;

public class CompradorView extends View {

	public Comprador realizarCadastro() {
		Comprador novoComprador = new Comprador();

		System.out.println("##--Cadastro do Comprador--##");
		System.out.println("|-----------------------------|");

		System.out.print("| E-mail: ");
		novoComprador.setEmail(sc.nextLine());

		System.out.print("| Senha: ");
		novoComprador.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: ");
		novoComprador.setNome(sc.nextLine());

		System.out.print("| Idade: ");
		novoComprador.setIdade(sc.nextLine());

		System.out.print("| Sexo: ");
		novoComprador.setSexo(sc.nextLine());

		System.out.print("| CPF: ");
		novoComprador.setCpf(sc.nextLine());

		System.out.print("| Telefone: ");
		novoComprador.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return novoComprador;
	}

	public Comprador atualizaComprador(Comprador compradorAutenticado) {
		// todo apertar enter para que nenhuma alteracao seja feita
		Comprador comprador = new Comprador();

		System.out.println("##--Atualizar dados do Comprador--##");
		System.out.println("|-----------------------------|");

		System.out.print("| E-mail: ");
		comprador.setEmail(sc.nextLine());

		System.out.print("| Senha: ");
		comprador.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: ");
		comprador.setNome(sc.nextLine());

		System.out.print("| Idade: ");
		comprador.setIdade(sc.nextLine());

		System.out.print("| Sexo: ");
		comprador.setSexo(sc.nextLine());

		System.out.print("| CPF: ");
		comprador.setCpf(sc.nextLine());

		System.out.print("| Telefone: ");
		comprador.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return comprador;
	}

	public void listarCompradores(List<Comprador> compradores) {

		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"nome", 20},
				new Object[]{"idade", 8},
				new Object[]{"sexo", 6},
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
						c.getSexo(),
						c.getCpf(),
						c.getEmail(),
						c.getTelefone()))
//				c.get
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}

	public void exibirMeusDados(Comprador comprador) {
		System.out.println("##   Meu perfil de comprador   ##");
		System.out.println("|---------------------------------------");
		System.out.println("| Email:    " + comprador.getEmail());
		System.out.println("| Nome:     " + comprador.getNome());
		System.out.println("| Idade:    " + comprador.getIdade());
		System.out.println("| Sexo:     " + comprador.getSexo());
		System.out.println("| Cpf:      " + comprador.getCpf());
		System.out.println("| Telefone: " + comprador.getTelefone());
//		System.out.println("| Endereco: " + comprador.getEndereco());
		System.out.println("|---------------------------------------");
	}

	public String requestSearchString() {
		System.out.println("Digite a string de busca: ");
		return sc.nextLine();
	}
}
