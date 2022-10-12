package view;

import model.Comprador;

import java.util.Scanner;

public class CompradorView {
	Scanner sc;

	public CompradorView() {

		this.sc = new Scanner(System.in);
	}

	public Integer getCompradorOption() {
		showOptionsComprador();
		return sc.nextInt();
	}

	private void showOptionsComprador() {
		System.out.print("\n##--Menu do Comprador--##\n\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("| Opcao 1 - Ver Propriedades  |\n");
		System.out.print("| Opcao 2 - Alterar Dados     |\n");
		System.out.print("| Opcao 3 - Consultar Dados   |\n");
		System.out.print("| Opcao 4 - Excluir Dados     |\n");
		System.out.print("| Opcao 5 - Sair              |\n");
		System.out.print("|-----------------------------|\n");
		System.out.print("Digite uma opcao: ");


	}

	public int compradorAlteraDados() {
		return 0;
	}

	public Comprador login(Comprador usuarioSendoEnviadoParaOFront) {
		return usuarioSendoEnviadoParaOFront;
	}
}
