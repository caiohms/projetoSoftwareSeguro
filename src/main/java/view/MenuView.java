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
        System.out.println("***********");
        System.out.println("Menu");
        System.out.println("1- Cadastras");
        System.out.println("2- Listar");
        System.out.println("outro- Sair");
    }
}
