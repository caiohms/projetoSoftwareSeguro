package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

	private final Scanner sc;

	public MainView() {
		this.sc = new Scanner(System.in);
	}

	public Integer getOption() {
		showOptionsMenu();
		int option = 0;
		boolean success = false;
		while (!success) {
			try {
				option = sc.nextInt();
				if (option > 0 && option < 6) success = true;
				else System.out.println("Opção indisponível, tente novamente.");
			} catch (InputMismatchException e) {
				System.out.println("Opção indisponível, tente novamente.");
			}
		}

		printSelectedOption(option);

		return option;
	}

	private void showOptionsMenu() {
		System.out.print("\n##-------LarDoceJar Menu-------##\n\n");
		System.out.print("|-------------------------------|\n");
		System.out.print("| Opcao 1 - Login Comprador     |\n");
		System.out.print("| Opcao 2 - Login Vendedor      |\n");
		System.out.print("| Opcao 3 - Cadastrar Uusario   |\n");
		System.out.print("| Opcao 4 - Login Administrador |\n");
		System.out.print("| Opcao 5 - Sair                |\n");
		System.out.print("|-------------------------------|\n");
		System.out.print("Digite uma opção: ");
	}

	private void printSelectedOption(int option) {
		switch (option) {
			case 1:
				System.out.println("Login como Comprador");
				break;
			case 2:
				System.out.println("Opcao de Login como Vendedor Selecionado");
				break;
			case 3:
				System.out.println("Opcao de Cadastro de Usuario");
				break;
			case 4:
				System.out.println();
				break;
			default:
				break;
		}
	}

	public void showQuit() {
		System.out.println("Saindo...");
	}
}
