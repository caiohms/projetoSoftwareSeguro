package controller;

import model.Usuario;
import view.MenuView;

public class MenuController {
    private final MenuView menuView;
    private final Usuario usuario;

    public MenuController(Usuario usuario) {
        this.usuario = usuario;

        this.menuView = new MenuView();

        while (true) {
            Integer userOption = menuView.getOption();
            switch (userOption) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                default:
                    break;
            }
        }

    }
}
