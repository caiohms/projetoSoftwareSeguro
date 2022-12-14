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
		divisor();
		System.out.println("ID:       " + comprador.getId());
		System.out.println("Email:    " + comprador.getEmail());
		System.out.println("Nome:     " + comprador.getNome());
		System.out.println("Idade:    " + comprador.getIdade());
		System.out.println("Genero:   " + comprador.getGenero());
		System.out.println("Cpf:      " + comprador.getCpf());
		System.out.println("Telefone: " + comprador.getTelefone());
		divisor();
	}

	public void exibirCorretor(Corretor corretor) {

		if (corretor == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##  Visualizar Corretor  ##");
		divisor();
		System.out.println("ID:       " + corretor.getId());
		System.out.println("Nome:     " + corretor.getNome());
		System.out.println("Idade:    " + corretor.getIdade());
		System.out.println("Sexo:     " + corretor.getSexo());
		System.out.println("Cpf:      " + corretor.getCpf());
		System.out.println("Telefone: " + corretor.getTelefone());
		divisor();
	}

	public void exibirVendedor(Vendedor vendedor) {

		if (vendedor == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##   Visualizar Vendedor   ##");
		divisor();
		System.out.println("ID:       " + vendedor.getId());
		System.out.println("Nome:     " + vendedor.getNome());
		System.out.println("Idade:    " + vendedor.getIdade());
		System.out.println("Sexo:     " + vendedor.getGenero());
		System.out.println("Cpf:      " + vendedor.getCpf());
		System.out.println("Telefone: " + vendedor.getTelefone());
		divisor();
	}

	public void exibirPropriedade(Propriedade propriedade) {

		if (propriedade == null) {
			idNotFound();
			confirmToContinue();
			return;
		}

		System.out.println("##   Visualizar Propriedade  ##");
		divisor();
		System.out.println("ID: " + propriedade.getId());
		System.out.println("Vendedor: " + propriedade.getVendedor());
		System.out.println("Corretor: " + propriedade.getCorretor());
		System.out.println("Status: " + propriedade.getStatus());
		System.out.println("Tipo: " + propriedade.getTipo());
		System.out.println("Descricao: " + propriedade.getDescricao());
		System.out.println("Area total: " + propriedade.getATotal());
		System.out.println("Area util: " + propriedade.getAUtil());
		System.out.println("Quartos: " + propriedade.getQuartos());
		System.out.println("Banheiros: " + propriedade.getBanheiros());
		System.out.println("Vagas garagem: " + propriedade.getVagasGaragem());
		System.out.println("Preco: " + propriedade.getPreco());
		System.out.println("Valor Condominio: " + propriedade.getValorCond());
		System.out.println("Logradouro: " + propriedade.getLogradouro());
		System.out.println("Numero: " + propriedade.getNumero());
		System.out.println("Complemento: " + propriedade.getComplemento());
		System.out.println("CEP: " + propriedade.getCep());
		System.out.println("Bairro: " + propriedade.getBairro());
		System.out.println("Estado: " + propriedade.getEstado());
		divisor();

	}

	public void exibirHistoricoBuscaComprador(List<HistoricoBusca> historico) {
		//todo
	}
}
