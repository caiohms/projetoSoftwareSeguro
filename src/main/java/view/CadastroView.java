package view;

import java.util.Scanner;

public class CadastroView {
	Scanner sc;

	public CadastroView() {

		this.sc = new Scanner(System.in);
	}

	public Integer getCadastroOption() {
		showOptionsCadastro();
		return sc.nextInt();
	}

	private void showOptionsCadastro() {
		System.out.print("\n##--Menu de Cadastro--##\n\n");
		System.out.print("|-------------------------------------|\n");
		System.out.print("| Opcao 1 - Cadastrar como Comprador  |\n");
		System.out.print("| Opcao 2 - Cadastrar como Vendedor   |\n");
		System.out.print("|-------------------------------------|\n");
		System.out.print("Digite uma opcao: ");


	}
}