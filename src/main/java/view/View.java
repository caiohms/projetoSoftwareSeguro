package view;

import controller.helper.OptionsMenu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class View {

	protected Scanner sc = new Scanner(System.in);

	public void solicitarIdParaConsulta() {
		System.out.print("Digite o id a ser consultado: ");
	}

	public boolean retryMenu() {
		return List.of("y", "yes").contains(sc.nextLine());
	}

	public int getNextInt() {
		return getNextInt("", "Entrada inválida para tipo inteiro. Tente novamente.");
	}

	public int getNextInt(String outMessage, String failMessage) {
		boolean valid = false;
		int val = 0;
		do {
			try {
				if (outMessage.length() > 0) {
					System.out.println(outMessage);
				}
				val = Integer.parseInt(sc.nextLine());
				valid = true;
			} catch (NumberFormatException nfe) {
				System.err.println(failMessage);
			}
		} while (!valid);
		return val;
	}

	public double getNextDouble(String outMessage, String failMessage) {
		boolean valid = false;
		double val = 0;
		do {
			try {
				if (outMessage.length() > 0) {
					System.out.println(outMessage);
				}
				val = Double.parseDouble(sc.nextLine());
				valid = true;
			} catch (NumberFormatException nfe) {
				System.err.println(failMessage);
			}
		} while (!valid);
		return val;
	}

	public boolean getNextBoolean(String outMessage) {
		System.out.println(outMessage);
		String nextline = sc.nextLine();
		return List.of("yes", "y", "YES", "1").contains(nextline);
	}

	protected Integer validatedIntegerInput(String out) {
		return getNextInt(out, "Entrada inválida para tipo inteiro. Tente novamente.");
	}

	protected Integer validatedLimitedIntegerOption(String out, int min, int max) {
		boolean valid = false;
		int val = 0;
		do try {
			if (out.length() > 0) {
				System.out.println(out);
			}
			String nextLine = sc.nextLine();
			val = Integer.parseInt(nextLine);
			if (val < min || val > max) {
				throw new InputMismatchException("");
			} else {
				valid = true;
			}
		} catch (InputMismatchException | NumberFormatException e) {
			sendOpcaoInvalida();
		} while (!valid);
		return val;
	}

	protected Double validatedDoubleInput(String out) {
		return getNextDouble(out, "Entrada inválida para tipo decimal. Tente novamente.");
	}

	protected String lengthLimitedStringInput(String out, int maxLen) {
		boolean valid = false;
		String in;
		do {
			System.out.println(out);
			in = sc.nextLine();
			if (in.length() > maxLen) {
				System.err.println("Excedeu tamanho máximo de caracteres. (" + maxLen + "). Tente novamente.");
			} else {
				valid = true;
			}
		} while (!valid);

		return in;
	}

	public void noChangesWereMade() {
		System.out.println("Nenhuma alteração foi efetuada.");
		confirmToContinue();
	}

	public void idNotFound() {
		System.out.println("ID não encontrado. Tente novamente...");
	}

	public void confirmToContinue() {
		System.out.print("Digite qualquer tecla para continuar...");
		sc.nextLine();
	}

	public void cadastroSuccess() {
		System.out.println("Cadastro efetuado com sucesso.");
	}

	public void atualizacaoSuccess() {
		System.out.println("Atualização de dados efetuada com sucesso.");
	}

	public void sendEmailAlreadyExists() {
		System.out.println("Erro: O email digitado já foi cadastrado.");
	}

	public void sendInvalidAge() {
		System.out.println("A idade digitada é inválida");
	}

	public void sendOpcaoInvalida() {
		System.out.println("Opcao invalida, tente novamente.");
	}

	public void noResults() {
		System.out.println("Nenhum resultado encontrado.");
	}

	public void oferecerCadastroEndereco(){
		System.out.println("Deseja cadastrar um endereco? [y/n]");
	}

	protected void divisor() {
		System.out.println("|---------------------------------------");
	}

	public void displayOptionsMenu(OptionsMenu optionsMenu) {
		System.out.println();
		System.out.println("##          " + optionsMenu.getTitle() + "         ##");
		System.out.println("|-----------" + "-".repeat(optionsMenu.getTitle().length()) + "-----------");

		for (int i = 0; i < optionsMenu.getOptionsList().length; i++) {
			System.out.println("| Opcao " + (i + 1) + " - " + optionsMenu.getOptionsList()[i].getOptionName());
		}

		System.out.println("| Opcao " + (optionsMenu.getOptionsList().length + 1) + " - Sair ");
		System.out.println("|-----------" + "-".repeat(optionsMenu.getTitle().length()) + "-----------");
		System.out.print("Digite uma opcao: ");
	}

	/**
	 * Retorna o valor em formato Integer, que deve ser entre min e max.
	 *
	 * @param min O parametro minimo, inclusivo.
	 * @param max O parametro maximo, inclusivo.
	 * @return O integer parsed.
	 */
	public Integer getLimitedOption(int min, int max) {
		boolean validOption = false;
		Integer option = null;

		while (!validOption) {
			try {
				option = getNextInt();
				if (option < min || option > max) {
					throw new InputMismatchException("");
				} else {
					validOption = true;
				}
			} catch (InputMismatchException e) {
				sendOpcaoInvalida();
				System.out.println("Digite uma opcao: ");
			}
		}

		return option;
	}

	protected void tabelarDados(List<Object[]> columns, List<List<?>> data) {
		List<Integer> columnLenghts = columns.stream().map(o -> (Integer) o[1]).collect(Collectors.toList());
		Object[] columnTitles = columns.stream().map(o -> (String) o[0]).toArray();

		int colCount = columns.size();
		String formattedDivisor = "+-%s-".repeat(colCount) + "+%n";
		Object[] divisorElements = columnLenghts.stream().map("-"::repeat).toArray();

		StringBuilder formattedRow = new StringBuilder();
		for (Integer c : columnLenghts) {
			formattedRow.append(String.format("| %%%d.%ds ", c, c - 2));
		}
		formattedRow.append("|%n");

		System.out.printf(formattedDivisor, divisorElements);
		System.out.printf(formattedRow.toString(), columnTitles);
		System.out.printf(formattedDivisor, divisorElements);

		for (List<?> o : data) {
			System.out.printf(formattedRow.toString(), o.toArray());
			System.out.printf(formattedDivisor, divisorElements);
		}
	}

	public boolean confirmarAcao(String acao) {
		System.out.println("##      !  Confirmar ação  !      ##");
		divisor();
		System.out.println("| Você está prestes a: " + acao);
		System.out.println("| Opcao 1 - Confirmar");
		System.out.println("| Opcao 2 - Voltar atrás");
		divisor();
		return getNextBoolean("Digite uma opcao: ");
	}

	protected Integer getEditingLimitedIntegerOption(String param, Integer originalValue, int min, int max) {
		boolean valid = false;
		int val = 0;
		do try {
			System.out.print("| " + param + " (" + originalValue.toString() + ") : ");
			String nextLine = sc.nextLine();
			if (nextLine.equals("")) {
				return originalValue;
			}
			val = Integer.parseInt(nextLine);
			if (val < min || val > max) {
				throw new InputMismatchException("");
			} else {
				valid = true;
			}
		} catch (InputMismatchException | NumberFormatException e) {
			sendOpcaoInvalida();
		} while (!valid);
		return val;
	}

	protected Integer getEditingIntegerOption(String param, Integer originalValue) {
		boolean valid = false;
		int val = 0;
		do try {
			System.out.print("| " + param + " (" + originalValue.toString() + ") : ");
			String nextLine = sc.nextLine();
			if (nextLine.equals("")) {
				return originalValue;
			}
			val = Integer.parseInt(nextLine);
			valid = true;
		} catch (InputMismatchException | NumberFormatException e) {
			sendOpcaoInvalida();
		} while (!valid);
		return val;
	}

	protected Double getEditingDoubleValue(String param, Double originalValue) {
		boolean valid = false;
		double val = 0;
		do try {
			System.out.print("| " + param + " (" + originalValue.toString() + ") : ");
			String nextLine = sc.nextLine();
			if (nextLine.equals("")) {
				return originalValue;
			}
			val = Double.parseDouble(nextLine);
			valid = true;
		} catch (NumberFormatException nfe) {
			System.err.println("Parametro invalido, tente novamente.");
		} while (!valid);
		return val;
	}

	protected String getEditingStringValue(String param, String originalValue, int maxLen) {
		boolean valid = false;
		String in = "";
		while (!valid) {
			System.out.print("| " + param + " ('" + originalValue + "') : ");
			in = sc.nextLine();
			if (in.length() > maxLen) {
				System.err.println("Excedeu tamanho máximo de caracteres (" + maxLen + "). Tente novamente.");
			} else if (in.equals("")) {
				return originalValue;
			} else {
				valid = true;
			}
		}
		return in;
	}

	protected String getEditingPasswordValue(String param, String originalValue, int maxLen) {
		boolean valid = false;
		String in = "";
		while (!valid) {
			System.out.print("| " + param + " ('****') : ");
			in = sc.nextLine();
			if (in.length() > maxLen) {
				System.err.println("Excedeu tamanho máximo de caracteres (" + maxLen + "). Tente novamente.");
			} else if (in.equals("")) {
				return originalValue;
			} else {
				valid = true;
			}
		}
		return in;
	}
}
