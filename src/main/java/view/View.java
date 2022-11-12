package view;

import controller.helper.OptionsMenu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class View {

	protected Scanner sc = new Scanner(System.in);

	protected void clearSystemIn() {
		sc = new Scanner(System.in);
	}

	public void solicitarIdParaConsulta() {
		System.out.print("Digite o id a ser consultado: ");
	}

	public Integer getInt() {
		return sc.nextInt();
	}

	public boolean retryMenu() {
		return List.of("y", "yes").contains(sc.nextLine());
	}

	protected Double validatedDoubleInput(String out) {
		boolean valid = false;
		Double val = null;
		do {
			System.out.println(out);
			try {
				val = Double.parseDouble(sc.nextLine());
				valid = true;
			} catch (NumberFormatException nfe) {
				System.err.println("Entrada inválida para tipo Decimal. Tente novamente.");
			}
		} while (!valid);
		return val;
	}

	protected Integer validatedIntegerInput(String out) {
		boolean valid = false;
		Integer val = null;
		do {
			System.out.println(out);
			try {
				val = Integer.parseInt(sc.nextLine());
				valid = true;
			} catch (NumberFormatException nfe) {
				System.err.println("Entrada inválida para tipo Decimal. Tente novamente.");
			}
		} while (!valid);
		return val;
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

	public Integer getLimitedOption(int min, int max) {
		boolean validOption = false;
		Integer option = null;

		while (!validOption) {
			try {
				option = sc.nextInt();
				if (option < min || option > max) {
					throw new InputMismatchException("");
				} else {
					validOption = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Opcao invalida, tente novamente.");
				System.out.println("Digite uma opcao: ");
				sc.next();
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

	public void noResults() {
		System.out.println("Nenhum resultado encontrado.");
	}
}
