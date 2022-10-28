package view;

import model.Adm;

import java.util.Scanner;

public class AdmView {

	Scanner sc;

	public AdmView() {
		this.sc = new Scanner(System.in);
	}

	public Integer getAdmOption() {
		showOptionsAdm();
		return sc.nextInt();
	}

	private void showOptionsAdm() {
		System.out.print("\n##--Menu do Administrador--##\n\n");
		System.out.print("|-----------------------------------------|\n");
		System.out.print("| Opcao 1 - Cadastrar Corretor            |\n");
		System.out.print("| Opcao 2 - Alterar Dados do Corretor     |\n");
		System.out.print("| Opcao 3 - Consultar Dados do Corretor   |\n");
		System.out.print("| Opcao 4 - Consultar Dados do Comprador  |\n");
		System.out.print("| Opcao 5 - Consultar Dados do Vendedor   |\n");
		System.out.print("| Opcao 6 - Excluir Corretor              |\n");
		System.out.print("| Opcao 7 - Sair                          |\n");
		System.out.print("|-----------------------------------------|\n");
		System.out.print("Digite uma opcao: ");


	}

	public Integer getIdCorretor() {
		showInsertAdm();
		return sc.nextInt();
	}

	private void showInsertAdm() {
		System.out.print("\n##--Menu do Administrador--##\n\n");
		System.out.print("|-------------------|\n");
		System.out.print("| Id do Corretor    |\n");
		System.out.print("|-------------------|\n");
		System.out.print("Digite o Id: ");


	}

	public int admAlteraDados() {
		return 0;
	}

	public Adm login(Adm usuarioSendoEnviadoParaOFront) {
		return usuarioSendoEnviadoParaOFront;
	}

}
