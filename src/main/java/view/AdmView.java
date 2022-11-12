package view;

import model.*;

import java.util.List;

public class AdmView extends View {

	public void admExibirComprador(Comprador comprador) {

		if (comprador == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##   Visualizar Comprador   ##");
		System.out.println("|---------------------------------------");
		System.out.println("ID:       " + comprador.getId());
		System.out.println("Email:    " + comprador.getEmail());
		System.out.println("Nome:     " + comprador.getNome());
		System.out.println("Idade:    " + comprador.getIdade());
		System.out.println("Sexo:     " + comprador.getSexo());
		System.out.println("Cpf:      " + comprador.getCpf());
		System.out.println("Telefone: " + comprador.getTelefone());
		System.out.println("|---------------------------------------");
	}

	public void exibirCorretor(Corretor corretor) {

		if (corretor == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##  Visualizar Corretor  ##");
		System.out.println("|---------------------------------------");
		System.out.println("ID:       " + corretor.getId());
		System.out.println("Nome:     " + corretor.getNome());
		System.out.println("Idade:    " + corretor.getIdade());
		System.out.println("Sexo:     " + corretor.getSexo());
		System.out.println("Cpf:      " + corretor.getCpf());
		System.out.println("Telefone: " + corretor.getTelefone());
		System.out.println("|---------------------------------------");
	}

	public void exibirVendedor(Vendedor vendedor) {

		if (vendedor == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##   Visualizar Vendedor   ##");
		System.out.println("|---------------------------------------");
		System.out.println("ID:       " + vendedor.getId());
		System.out.println("Nome:     " + vendedor.getNome());
		System.out.println("Idade:    " + vendedor.getIdade());
		System.out.println("Sexo:     " + vendedor.getGenero());
		System.out.println("Cpf:      " + vendedor.getCpf());
		System.out.println("Telefone: " + vendedor.getTelefone());
		System.out.println("|---------------------------------------");
	}

	public void exibirPropriedade(Propriedade propriedade) {

		if (propriedade == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		//TODO

	}

	public void exibirHistoricoBuscaComprador(List<HistoricoBusca> historico) {
		//todo
	}
}
