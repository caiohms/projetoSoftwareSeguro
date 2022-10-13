package view;

import form.CompradorForm;
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
	public Integer addCompradorOption() {
		addCompradorMenu();
		return sc.nextInt();
	}
	private CompradorForm addCompradorMenu(){
		System.out.print("\n##--Cadastro do Comprador--##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| E-mail: \n");
		String email = sc.nextLine();
		System.out.print("| Senha: \n");
		String senha = sc.nextLine();
		System.out.print("| Nome Completo: \n");
		String nome = sc.nextLine();
		System.out.print("| Idade: \n");
		String idade = sc.nextLine();
		System.out.print("| Sexo: \n");
		String sexo = sc.nextLine();
		System.out.print("| CPF: \n");
		String cpf = sc.nextLine();
		System.out.print("| Telefone: \n");
		String telefone = sc.nextLine();
		System.out.print("| Endereco: \n");
		String endereco = sc.nextLine();

		return new CompradorForm(email, senha, nome, idade, sexo, cpf, telefone, endereco);
	}

	public int compradorAlteraDados() {
		return 0;
	}

	public Comprador login(Comprador usuarioSendoEnviadoParaOFront) {
		return usuarioSendoEnviadoParaOFront;
	}
}
