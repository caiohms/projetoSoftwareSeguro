package view;

import model.Comprador;

import java.util.Scanner;

public class CompradorView {
	Scanner sc;

	public CompradorView() {
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

	public int compradorAlteraDados() {
		return 0;
	}

	public Comprador login(Comprador usuarioSendoEnviadoParaOFront) {
		return usuarioSendoEnviadoParaOFront;
	}

	public Comprador realizarCadastro() {
		Comprador novoComprador = new Comprador();

		System.out.println("\n##--Cadastro do Comprador--##\n");
		System.out.println("|-----------------------------|");

		System.out.print("| E-mail: \n");
		novoComprador.setEmail(sc.nextLine());

		System.out.print("| Senha: \n");
		novoComprador.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: \n");
		novoComprador.setNome(sc.nextLine());

		System.out.print("| Idade: \n");
		novoComprador.setIdade(sc.nextLine());

		System.out.print("| Sexo: \n");
		novoComprador.setSexo(sc.nextLine());

		System.out.print("| CPF: \n");
		novoComprador.setCpf(sc.nextLine());

		System.out.print("| Telefone: \n");
		novoComprador.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return novoComprador;
	}

	public Comprador atualizaComprador() {
		Comprador comprador = new Comprador();

		System.out.println("\n##--Atualizar dados do Comprador--##\n");
		System.out.println("|-----------------------------|");

		System.out.print("| E-mail: \n");
		comprador.setEmail(sc.nextLine());

		System.out.print("| Senha: \n");
		comprador.setPassword(sc.nextLine());

		System.out.print("| Nome Completo: \n");
		comprador.setNome(sc.nextLine());

		System.out.print("| Idade: \n");
		comprador.setIdade(sc.nextLine());

		System.out.print("| Sexo: \n");
		comprador.setSexo(sc.nextLine());

		System.out.print("| CPF: \n");
		comprador.setCpf(sc.nextLine());

		System.out.print("| Telefone: \n");
		comprador.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return comprador;
	}

	public Integer getDeleteOption() {
		deletaComprador();
		return sc.nextInt();
	}

	public void deletaComprador() {
		System.out.print("\n##--Voce deseja deletar o usuario --##\n\n");
		System.out.print("|----------------------------------|\n");
		System.out.print("| Opcao 1 - Sim, quero deletar     |\n");
		System.out.print("| Opcao 2 - Não, não quero deletar |\n");
		System.out.print("|----------------------------------|\n");
		System.out.print("Digite uma opcao: ");

	}

	public void cadastroSuccess() {
		System.out.println("Cadastro efetuado com sucesso.");
	}

	public void atualizacaoSuccess() {
		System.out.println("Atualização de dados efetuada com sucesso.");
	}
}
