package view;

import model.Comprador;
import model.Corretor;
import model.Vendedor;

public class AdmView extends View {

	public Integer getIdVendedor() {
		showInsertVendedor();
		return sc.nextInt();
	}

	public void showInsertVendedor() {
		System.out.print("\n##--Menu do Administrador--##\n\n");
		System.out.print("|-------------------|\n");
		System.out.print("| Id do Vendedor    |\n");
		System.out.print("|-------------------|\n");
		System.out.print("Digite o Id: ");
	}

	public void exibirCorretor(Corretor corretor) {

		if (corretor == null) {
			System.out.println("Corretor não encontrado.");
			return;
		}

		System.out.println("##  Visualizar Corretor  ##");
		System.out.println("|-------------------|");
		System.out.println("ID:       " + corretor.getId());
		System.out.println("Nome:     " + corretor.getNome());
		System.out.println("Idade:    " + corretor.getIdade());
		System.out.println("Sexo:     " + corretor.getSexo());
		System.out.println("Cpf:      " + corretor.getCpf());
		System.out.println("Telefone: " + corretor.getTelefone());
		System.out.println("|-------------------|");
	}

	public void exibirComprador(Comprador comprador) {
		System.out.println("##   Visualizar Comprador   ##");
		System.out.println("|----------------------------|");
		System.out.println("ID:       " + comprador.getId());
		System.out.println("Email:    " + comprador.getEmail());
		System.out.println("Nome:     " + comprador.getNome());
		System.out.println("Idade:    " + comprador.getIdade());
		System.out.println("Sexo:     " + comprador.getSexo());
		System.out.println("Cpf:      " + comprador.getCpf());
		System.out.println("Telefone: " + comprador.getTelefone());
		System.out.println("|----------------------------|");
	}

	public void consultarVendedor(Vendedor vendedor) {
		System.out.println("##   Visualizar Vendedor   ##");
		System.out.println("|-------------------|");
		System.out.println("ID:       " + vendedor.getId());
		System.out.println("Nome:     " + vendedor.getNome());
		System.out.println("Idade:    " + vendedor.getIdade());
		System.out.println("Sexo:     " + vendedor.getSexo());
		System.out.println("Cpf:      " + vendedor.getCpf());
		System.out.println("Telefone: " + vendedor.getTelefone());
		System.out.println("|-------------------|");
	}

}
