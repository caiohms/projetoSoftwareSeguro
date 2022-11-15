package view;

import model.Vendedor;

import java.util.List;
import java.util.stream.Collectors;

public class VendedorView extends View {

	public Vendedor realizarCadastro() {
		Vendedor novoVendedor = new Vendedor();

		System.out.println("##   Cadastro do Vendedor   ##");
		divisor();

		String email = lengthLimitedStringInput("| E-mail: ", 100);
		String senha = lengthLimitedStringInput("| Senha: ", 255);
		String nome = lengthLimitedStringInput("| Nome Completo: ", 50);
		String idade = lengthLimitedStringInput("| Idade: ", 2);
		String genero = lengthLimitedStringInput("| Genero: ", 1);
		String cpf = lengthLimitedStringInput("| CPF: ", 11);
		String telefone = lengthLimitedStringInput("| Telefone: ", 13);
		novoVendedor.setEmail(email);
		novoVendedor.setPassword(senha);
		novoVendedor.setNome(nome);
		novoVendedor.setIdade(idade);
		novoVendedor.setGenero(genero);
		novoVendedor.setCpf(cpf);
		novoVendedor.setTelefone(telefone);
		return novoVendedor;
	}

	public Vendedor atualizarVendedor(Vendedor vendedorAutenticado) {
		Vendedor vModificado = new Vendedor();

		System.out.println("##  Atualizar dados do Vendedor  ##");
		divisor();
		System.out.println("| Nota: deixe em branco para não realizar alterações no campo.");

		vModificado.setEmail(getEditingStringValue("Email", vendedorAutenticado.getEmail(), 100));
		vModificado.setPassword(getEditingPasswordValue("Senha", vendedorAutenticado.getPassword(), 100));
		vModificado.setNome(getEditingStringValue("Nome Completo", vendedorAutenticado.getNome(), 255));
		vModificado.setIdade(getEditingStringValue("Idade", vendedorAutenticado.getIdade(), 2));
		vModificado.setGenero(getEditingStringValue("Genero", vendedorAutenticado.getGenero(), 1));
		vModificado.setCpf(getEditingStringValue("CPF", vendedorAutenticado.getCpf(), 11));
		vModificado.setTelefone(getEditingStringValue("Telefone", vendedorAutenticado.getTelefone(), 13));
		// endereco

		return vModificado;
	}

	public void exibirDados(Vendedor vendedorAutenticado) {
		System.out.println("\n##-- Dados do vendedor autenticado --##\n");
		divisor();
		System.out.println("| E-mail: " + vendedorAutenticado.getEmail());
		System.out.println("| Nome Completo: " + vendedorAutenticado.getNome());
		System.out.println("| Idade: " + vendedorAutenticado.getIdade());
		System.out.println("| Sexo: " + vendedorAutenticado.getGenero());
		System.out.println("| CPF: " + vendedorAutenticado.getCpf());
		System.out.println("| Telefone: " + vendedorAutenticado.getTelefone());
	}

	public void listarVendedores(List<Vendedor> vendedores) {
		List<Object[]> columns = List.of(
				new Object[]{"id", 5},
				new Object[]{"nome", 40},
				new Object[]{"idade", 8},
				new Object[]{"genero", 10},
				new Object[]{"telefone", 15}
		);

		List<List<?>> data = vendedores
				.stream()
				.map(v -> List.of(
						v.getId(),
						v.getNome(),
						v.getIdade(),
						v.getGenero(),
						v.getTelefone()))
				.collect(Collectors.toList());

		tabelarDados(columns, data);
	}
}
