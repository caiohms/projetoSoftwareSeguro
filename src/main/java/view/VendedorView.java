package view;

import model.Vendedor;

public class VendedorView extends View {

	public Integer getVendedorOption() {
		showOptionsVendedor();
		return sc.nextInt();
	}

	private void showOptionsVendedor() {
		System.out.println("\n##--Menu do Vendedor--##\n");
		System.out.println("|-----------------------------------------------------|");
		System.out.println("| Opcao 1 - Consultar minhas propriedades cadastradas |");
		System.out.println("| Opcao 2 - Anunciar nova propriedade                 |");
		System.out.println("| Opcao 3 - Alterar meus dados                        |");
		System.out.println("| Opcao 4 - Consultar meus dados                      |");
		System.out.println("| Opcao 5 - Excluir meu cadastro                      |");
		System.out.println("| Outro - Sair                                        |");
		System.out.println("|-----------------------------------------------------|");
		System.out.print("Digite uma opcao: ");
	}

	public Vendedor realizarCadastro() {
		Vendedor novoVendedor = new Vendedor();

		System.out.println("\n##--Cadastro do Vendedor--##\n");
		System.out.println("|-----------------------------|");

		System.out.println("| E-mail: ");
		novoVendedor.setEmail(sc.nextLine());

		System.out.println("| Senha: ");
		novoVendedor.setPassword(sc.nextLine());

		System.out.println("| Nome Completo: ");
		novoVendedor.setNome(sc.nextLine());

		System.out.println("| Idade: ");
		novoVendedor.setIdade(sc.nextLine());

		System.out.println("| Sexo: ");
		novoVendedor.setSexo(sc.nextLine());

		System.out.println("| CPF: ");
		novoVendedor.setCpf(sc.nextLine());

		System.out.println("| Telefone: ");
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
		vendedor.setSexo(sc.nextLine());

		System.out.print("| CPF: \n");
		vendedor.setCpf(sc.nextLine());

		System.out.print("| Telefone: \n");
		vendedor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

		return vendedor;
	}

	public Integer getDeleteOption() {
		deletaVendedor();
		return sc.nextInt();
	}

	public void deletaVendedor() {
		System.out.println("\n##--Voce deseja deletar o usuario --##\n");
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

	public void exibirDados(Vendedor vendedorAutenticado) {

		System.out.println("\n##-- Dados do vendedor autenticado --##\n");
		System.out.println("|-----------------------------|");
		System.out.println("| E-mail: " + vendedorAutenticado.getEmail());
		System.out.println("| Nome Completo: " + vendedorAutenticado.getNome());
		System.out.println("| Idade: " + vendedorAutenticado.getIdade());
		System.out.println("| Sexo: " + vendedorAutenticado.getSexo());
		System.out.println("| CPF: " + vendedorAutenticado.getCpf());
		System.out.println("| Telefone: " + vendedorAutenticado.getTelefone());
	}
}