package view;

import model.Corretor;

import java.util.Scanner;

public class CorretorView {
	private final Scanner sc;

	public CorretorView() {
		this.sc = new Scanner(System.in);
	}

	public Integer getCompradorOption() {
		showOptionsComprador();
		return sc.nextInt();
	}

	private void showOptionsComprador() {
		System.out.print("\n##--Menu do Comprador--##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| Opcao 1 - Ver Propriedades  |\n");
		System.out.print("| Opcao 2 - Alterar Dados     |\n");
		System.out.print("| Opcao 3 - Consultar Dados   |\n");
		System.out.print("| Opcao 4 - Excluir Dados     |\n");
		System.out.print("| Opcao 5 - Sair              |\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("Digite uma opcao: ");
	}

	public Corretor realizarCadastro() {
		Corretor novoCorretor = new Corretor();

		System.out.println("\n##--Cadastro do Corretor--##\n");
		System.out.println("|-----------------------------|");

		System.out.println("| E-mail: ");
		novoCorretor.setEmail(sc.nextLine());

		System.out.println("| Senha: ");
		novoCorretor.setPassword(sc.nextLine());

		System.out.println("| Nome Completo: ");
		novoCorretor.setNome(sc.nextLine());

		System.out.println("| Idade: ");
		novoCorretor.setIdade(sc.nextLine());

		System.out.println("| Sexo: ");
		novoCorretor.setSexo(sc.nextLine());

		System.out.println("| CPF: ");
		novoCorretor.setCpf(sc.nextLine());

		System.out.println("| Telefone: ");
		novoCorretor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return novoCorretor;
	}

	public Corretor atualizaCorretor() {
		Corretor corretor = new Corretor();

		System.out.println();
		System.out.println("##--Atualizar dados do Corretor--##");
		System.out.println("|-----------------------------|");

		System.out.println("| E-mail: ");
		corretor.setEmail(sc.nextLine());

		System.out.println("| Senha: ");
		corretor.setPassword(sc.nextLine());

		System.out.println("| Nome Completo: ");
		corretor.setNome(sc.nextLine());

		System.out.println("| Idade: ");
		corretor.setIdade(sc.nextLine());

		System.out.println("| Sexo: ");
		corretor.setSexo(sc.nextLine());

		System.out.println("| CPF: ");
		corretor.setCpf(sc.nextLine());

		System.out.println("| Telefone: ");
		corretor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return corretor;
	}

	public Integer getDeleteOption() {
		deletaCorretor();
		return sc.nextInt();
	}

	public void deletaCorretor() {
		System.out.println("\n##--Voce deseja deletar o usuario --##");
		System.out.println("|----------------------------------|");
		System.out.println("| Opcao 1 - Sim, quero deletar     |");
		System.out.println("| Opcao 2 - Não, não quero deletar |");
		System.out.println("|----------------------------------|");
		System.out.print("Digite uma opcao: ");

	}

	public void cadastroSuccess() {
		System.out.println("Cadastro efetuado com sucesso.");
	}

	public void atualizacaoSuccess() {
		System.out.println("Atualização de dados efetuada com sucesso.");
	}
}
