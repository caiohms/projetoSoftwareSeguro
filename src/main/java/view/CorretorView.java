package view;

import model.Corretor;

public class CorretorView extends View {

	public Corretor realizarCadastro() {
		Corretor novoCorretor = new Corretor();

		System.out.println("\n##--Cadastro do Corretor--##\n");
		divisor();

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
		divisor();

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

	public void deletaCorretor() {
		System.out.println("\n##--Voce deseja deletar o usuario --##");
		divisor();
		System.out.println("| Opcao 1 - Sim, quero deletar     |");
		System.out.println("| Opcao 2 - Não, não quero deletar |");
		divisor();
		System.out.print("Digite uma opcao: ");
	}
}
