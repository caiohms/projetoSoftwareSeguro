package view;

import model.Corretor;

import java.util.Scanner;

public class CorretorView {
    Scanner sc;

    public CorretorView() {
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

    public Corretor login(Corretor usuarioSendoEnviadoParaOFront) {
        return usuarioSendoEnviadoParaOFront;
    }

    public Corretor realizarCadastro() {
        Corretor novoCorretor = new Corretor();

        System.out.println("\n##--Cadastro do Corretor--##\n");
        System.out.println("|-----------------------------|");

        System.out.print("| E-mail: \n");
        novoCorretor.setEmail(sc.nextLine());

        System.out.print("| Senha: \n");
        novoCorretor.setPassword(sc.nextLine());

        System.out.print("| Nome Completo: \n");
        novoCorretor.setNome(sc.nextLine());

        System.out.print("| Idade: \n");
        novoCorretor.setIdade(sc.nextLine());

        System.out.print("| Sexo: \n");
        novoCorretor.setSexo(sc.nextLine());

        System.out.print("| CPF: \n");
        novoCorretor.setCpf(sc.nextLine());

        System.out.print("| Telefone: \n");
        novoCorretor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

        return novoCorretor;
    }
    public Corretor atualizaCorretor() {
        Corretor corretor = new Corretor();

        System.out.println("\n##--Atualizar dados do Corretor--##\n");
        System.out.println("|-----------------------------|");

        System.out.print("| E-mail: \n");
        corretor.setEmail(sc.nextLine());

        System.out.print("| Senha: \n");
        corretor.setPassword(sc.nextLine());

        System.out.print("| Nome Completo: \n");
        corretor.setNome(sc.nextLine());

        System.out.print("| Idade: \n");
        corretor.setIdade(sc.nextLine());

        System.out.print("| Sexo: \n");
        corretor.setSexo(sc.nextLine());

        System.out.print("| CPF: \n");
        corretor.setCpf(sc.nextLine());

        System.out.print("| Telefone: \n");
        corretor.setTelefone(sc.nextLine());

//		System.out.print("| Endereco: \n");
//		String endereco = sc.nextLine();
//		novoComprador.setPassword(sc.nextLine());

        return corretor;
    }

    public Integer getDeleteOption() {
        deletaCorretor();
        return sc.nextInt();
    }

    public void deletaCorretor() {
        System.out.print("\n##--Voce deseja deletar o usuario --##\n\n");
        System.out.print("|----------------------------------|\n");
        System.out.print("| Opcao 1 - Sim, quero deletar     |\n");
        System.out.print("| Opcao 2 - Não, não quero deletar |\n");
        System.out.print("|----------------------------------|\n");
        System.out.print("Digite uma opcao: ");

    }
    public void cadastroSuccess() {
        System.out.println("Cadastro efetuado com sucesso.");
    }
    public void atualizacaoSuccess() {
        System.out.println("Atualização de dados efetuada com sucesso.");
    }
}