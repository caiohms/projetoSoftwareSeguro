package view;

import java.util.Scanner;

public class MenuView {

    Scanner sc;

    public MenuView() {
        this.sc = new Scanner(System.in);
    }

    public Integer getOption() {
        showOptionsMenu();
        return sc.nextInt();
    }

    private void showOptionsMenu() {
        System.out.print("\n##-------LarDoceJar Menu-------##\n\n");
        System.out.print("|-------------------------------|\n");
        System.out.print("| Opcao 1 - Login Comprador     |\n");
        System.out.print("| Opcao 2 - Login Vendedor      |\n");
        System.out.print("| Opcao 3 - Cadastrar Uusario   |\n");
        System.out.print("| Opcao 4 - Sair                |\n");
        System.out.print("|-------------------------------|\n");
        System.out.print("Digite uma opção: ");


    }
}
