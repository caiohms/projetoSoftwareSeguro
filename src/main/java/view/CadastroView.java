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
		System.out.println("##--Menu de Cadastro--##");
		System.out.println("|-------------------------------------|");
		System.out.println("| Opcao 1 - Cadastrar como Comprador  |");
		System.out.println("| Opcao 2 - Cadastrar como Vendedor   |");
		System.out.println("|-------------------------------------|");
		System.out.println("Digite uma opcao: ");
	}
}