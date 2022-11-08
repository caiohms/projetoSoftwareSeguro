package view;

import model.Usuario;

public class AutenticacaoView extends View {

	public Usuario loginForm() {

		Usuario usuario = new Usuario();

		System.out.print("\nE-mail: ");
		usuario.setEmail(sc.nextLine());

		System.out.print("Senha: ");
		usuario.setPassword(sc.nextLine());

		return usuario;
	}

	public void usuarioAutenticado() {
		System.out.println("Usuario autenticado.");
	}

	public void usuarioNaoAutenticado() {
		System.out.println("Usuario não autenticado, deseja tentar novamente? [y/n]");
	}

}
