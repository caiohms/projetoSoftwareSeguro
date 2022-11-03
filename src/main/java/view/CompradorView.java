package view;

import model.Comprador;

public class CompradorView extends View {

	public Integer getCompradorOption() {
		showOptionsComprador();
		return sc.nextInt();
	}

	private void showOptionsComprador() {
		System.out.println("##--Menu do Comprador--##");
		System.out.println("|-----------------------------|");
		System.out.println("| Opcao 1 - Ver Propriedades  |");
		System.out.println("| Opcao 2 - Alterar Dados     |");
		System.out.println("| Opcao 3 - Consultar Dados   |");
		System.out.println("| Opcao 4 - Excluir Dados     |");
		System.out.println("| Opcao 5 - Sair              |");
		System.out.println("|-----------------------------|");
		System.out.print("Digite uma opcao: ");
	}

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

	public Comprador atualizaComprador() {
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

	public Integer getDeleteOption() {
		deletaComprador();
		return sc.nextInt();
	}

	public void deletaComprador() {
		System.out.println("## Deseja mesmo deletar o usuario? ##");
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

	public void sendEmailAlreadyExists() {
		System.out.println("Erro: O email digitado já foi cadastrado.");
	}

	public void sendInvalidAge() {
		System.out.println("A idade digitada é inválida");
	}
}
