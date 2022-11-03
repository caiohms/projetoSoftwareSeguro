package view;

import java.util.InputMismatchException;

public class CadastroView extends View {

	public Integer getCadastroOption() {
		boolean validOption = false;
		Integer option = null;

		while (!validOption) {
			showOptionsCadastro();

			try {
				option = sc.nextInt();
				if (option < 1 || option > 2) {
					throw new InputMismatchException("");
				} else {
					validOption = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opcao invalida, tente novamente.");
			}
		}

		return option;
	}

	private void showOptionsCadastro() {
		System.out.println();
		System.out.println("##  Menu de Cadastro  ##");
		System.out.println("|-------------------------------------|");
		System.out.println("| Opcao 1 - Cadastrar como Comprador  |");
		System.out.println("| Opcao 2 - Cadastrar como Vendedor   |");
		System.out.println("|-------------------------------------|");
		System.out.print("| Digite uma opcao: ");
	}
}