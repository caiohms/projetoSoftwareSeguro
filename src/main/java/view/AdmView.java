package view;

import model.Adm;
import model.Corretor;

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
        showInsertCorretor();
        return sc.nextInt();
    }

    private void showInsertCorretor() {
        System.out.print("\n##--Menu do Administrador--##\n\n");
        System.out.print("|-------------------|\n");
        System.out.print("| Id do Corretor    |\n");
        System.out.print("|-------------------|\n");
        System.out.print("Digite o Id: ");
    }

    public Integer getIdVendedor(){
        showInsertVendedor();
        return sc.nextInt();
    }

    public void showInsertVendedor(){
        System.out.print("\n##--Menu do Administrador--##\n\n");
        System.out.print("|-------------------|\n");
        System.out.print("| Id do Vendedor    |\n");
        System.out.print("|-------------------|\n");
        System.out.print("Digite o Id: ");
    }

    public void consultarCorretor(Corretor corretor){
        System.out.println("##--Vizualizar Corretor--##\n");
        System.out.println("|-------------------|");
        System.out.println("ID: "+corretor.getId());
        System.out.println("Nome: "+corretor.getNome());
        System.out.println("Idade: "+corretor.getIdade());
        System.out.println("Sexo: "+corretor.getSexo());
        System.out.println("Cpf: "+corretor.getCpf());
        System.out.println("Telefone: "+corretor.getTelefone());
        System.out.println("|-------------------|");
    }

    public int admAlteraDados() {
        return 0;
    }

    public Adm login(Adm usuarioSendoEnviadoParaOFront) {
        return usuarioSendoEnviadoParaOFront;
    }

}
