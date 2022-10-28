package view;

import model.Usuario;

import java.util.List;
import java.util.Scanner;

public class AutenticacaoView {

	private final Scanner sc;

	public AutenticacaoView() {
		this.sc = new Scanner(System.in);
	}

	public Usuario login() {

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

	public boolean retryMenu() {
		return List.of("y", "yes").contains(sc.nextLine());
	}

}
