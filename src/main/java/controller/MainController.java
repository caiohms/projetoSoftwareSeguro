package controller;

import model.Usuario;
import view.MenuView;
import controller.AutenticacaoController;
import view.CadastroView;
import controller.CadastroController;
public class MainController {
    private final MenuView menuView;
    private Usuario user;

    public MainController() {
        this.menuView = new MenuView();

        while (true) {
            Integer userOption = menuView.getOption();
            switch (userOption) {
                case 1:
                    System.out.print("\nOpcao de Login como Comprador Selecionado");
                    this.user = new AutenticacaoController().getUsuario();
                    new CompradorController();
                    break;

                case 2:
                    System.out.print("\nOpcao de Login como Vendedor Selecionado");
                    new AutenticacaoController();
                    break;

                case 3:
                    System.out.print("\nOpcao de Cadastro de Usuario\n");
                    new CadastroController();
                    break;

                default:
                    System.out.print("\nOpcao Invalida!");
                    break;
            }
        }

    }
}
