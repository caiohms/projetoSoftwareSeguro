package controller;

import controller.helper.MenuOption;
import controller.helper.OptionsMenu;
import view.CadastroView;

public class CadastroController {

	public CadastroController() {

		CadastroView cadastroView = new CadastroView();

		new OptionsMenu()
				.withTitle("Realizar cadastro")
				.withOptions(
						new MenuOption("Cadastrar como Comprador", () -> new CompradorController().realizarCadastro()),
						new MenuOption("Cadastrar como Vendedor", () -> new VendedorController().realizarCadastro())
				)
				.runOnceInView(cadastroView);
	}
}

