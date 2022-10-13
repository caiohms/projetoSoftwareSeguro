package view;

import model.Vendedor;

import java.util.Scanner;

public class VendedorView {
    Scanner sc;

    public VendedorView() {
        this.sc = new Scanner(System.in);
    }

    public Integer getVendedorOption(){
        showOptionsVendedor();
        return sc.nextInt();
    }

    private void showOptionsVendedor(){
        System.out.println("\n##--Menu do Vendedor--##\n");
        System.out.println("|-----------------------------|");
        System.out.println("| Opcao 1 - Vender um Imóvel  |");
        System.out.println("| Opcao 2 - Alterar Dados     |");
        System.out.println("| Opcao 3 - Consultar Dados   |");
        System.out.println("| Opcao 4 - Excluir Dados     |");
        System.out.println("| Opcao 5 - Sair              |");
        System.out.println("|-----------------------------|");
        System.out.println("Digite uma opcao: ");
    }

    public int vendedorAlterarDados(){
        return 0;
    }

    public Vendedor login(Vendedor usuarioSendoEnviadoParaOFront) {
        return usuarioSendoEnviadoParaOFront;
    }

    public Vendedor realizarCadastro() {
        Vendedor novoVendedor = new Vendedor();

        System.out.println("\n##--Cadastro do Comprador--##\n");
        System.out.println("|-----------------------------|");

        System.out.print("| E-mail: \n");
        novoVendedor.setEmail(sc.nextLine());

        System.out.print("| Senha: \n");
        novoVendedor.setPassword(sc.nextLine());

        System.out.print("| Nome Completo: \n");
        novoVendedor.setNome(sc.nextLine());

        System.out.print("| Idade: \n");
        novoVendedor.setIdade(sc.nextLine());

        System.out.print("| Sexo: \n");
        novoVendedor.setSexo(sc.nextLine());

        System.out.print("| CPF: \n");
        novoVendedor.setCpf(sc.nextLine());

        System.out.print("| Telefone: \n");
        novoVendedor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

        return novoVendedor;
    }

    public Vendedor atualizarVendedor() {
        Vendedor vendedor = new Vendedor();

        System.out.println("\n##--Atualizar dados do Comprador--##\n");
        System.out.println("|-----------------------------|");

        System.out.print("| E-mail: \n");
        vendedor.setEmail(sc.nextLine());

        System.out.print("| Senha: \n");
        vendedor.setPassword(sc.nextLine());

        System.out.print("| Nome Completo: \n");
        vendedor.setNome(sc.nextLine());

        System.out.print("| Idade: \n");
        vendedor.setIdade(sc.nextLine());

        System.out.print("| Sexo: \n");
        vendedor.setSexo(sc.nextLine());

        System.out.print("| CPF: \n");
        vendedor.setCpf(sc.nextLine());

        System.out.print("| Telefone: \n");
        vendedor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

        return vendedor;
    }

    public Integer getDeleteOption() {
        deletaVendedor();
        return sc.nextInt();
    }

    public void deletaVendedor() {
        System.out.println("\n##--Voce deseja deletar o usuario --##\n");
        System.out.println("|----------------------------------|");
        System.out.println("| Opcao 1 - Sim, quero deletar     |");
        System.out.println("| Opcao 2 - Não, não quero deletar |");
        System.out.println("|----------------------------------|");
        System.out.println("Digite uma opcao: ");
    }

    public void cadastroSuccess() {
        System.out.println("Cadastro efetuado com sucesso.");
    }
    public void atualizacaoSuccess() {
        System.out.println("Atualização de dados efetuada com sucesso.");
    }
}