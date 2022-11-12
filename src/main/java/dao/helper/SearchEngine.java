package dao.helper;

import dao.PropriedadeDAO;
import dao.VendedorDAO;
import model.Propriedade;
import model.Vendedor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.FuzzyScore;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
	private final PropriedadeDAO propriedadeDAO = new PropriedadeDAO();
	private final VendedorDAO vendedorDAO = new VendedorDAO();

	private final List<String> stopWords = List.of("i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "should", "now");

	public List<Propriedade> getPropriedadesPorDescricao(String searchString) {
		List<Propriedade> propriedadeList = propriedadeDAO.getAll();
		Map<Integer, Propriedade> propedadeMap = propriedadeList.stream()
				.collect(Collectors.toMap(Propriedade::getId, p -> p, (a, b) -> b));

		String input = StringUtils.join(tokenizeInput(searchString), "");
		Map<Integer, String> descriptionTermsMap = tokenizeDescriptions(propedadeMap);
		Map<Integer, Integer> scoreMap = new LinkedHashMap<>();

		FuzzyScore fuzzyScore = new FuzzyScore(Locale.ROOT);

		descriptionTermsMap.forEach((index, description) -> {
			Integer score = fuzzyScore.fuzzyScore(description, input);
			if (score != 0) {
				scoreMap.put(index, score);
			}
		});

		List<Propriedade> result = new ArrayList<>();

		scoreMap.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> o2 - o1))
				.forEach(entry -> result.add(propedadeMap.get(entry.getKey())));

		return result;
	}

	private ArrayList<String> tokenizeInput(String searchString) {
		String str = StringUtils.stripAccents(searchString);

		ArrayList<String> list = Arrays.stream(str.split("\\s+")).map(String::trim)
				.collect(Collectors.toCollection(ArrayList::new));
		list.removeAll(stopWords);

		return list;
	}

	private Map<Integer, String> tokenizeDescriptions(Map<Integer, Propriedade> propriedadeMap) {
		Map<Integer, String> descriptionTermsMap = new HashMap<>();

		for (Map.Entry<Integer, Propriedade> entry : propriedadeMap.entrySet()) {
			String description = StringUtils.stripAccents(entry.getValue().getDescricao());
			ArrayList<String> terms = Arrays.stream(description.split("\\s+")).map(String::trim)
					.collect(Collectors.toCollection(ArrayList::new));
			terms.removeAll(stopWords);
			descriptionTermsMap.put(entry.getKey(), StringUtils.join(terms, " "));
		}
		return descriptionTermsMap;
	}

	public List<Vendedor> getVendedorPorNome(String searchString) {
		List<Vendedor> vendedorList = vendedorDAO.getAll();
		Map<Integer, Vendedor> vendedorMap = vendedorList.stream()
				.collect(Collectors.toMap(Vendedor::getId, p -> p, (a, b) -> b));

		Map<Integer, String> nomesMap = new HashMap<>();
		for (Map.Entry<Integer, Vendedor> entry : vendedorMap.entrySet()) {
			nomesMap.put(entry.getKey(), entry.getValue().getNome());
		}

		Map<Integer, Integer> scoreMap = new LinkedHashMap<>();
		FuzzyScore fuzzyScore = new FuzzyScore(Locale.ROOT);

		nomesMap.forEach((index, nome) -> {
			Integer score = fuzzyScore.fuzzyScore(nome, searchString);
			if (score != 0) {
				scoreMap.put(index, score);
			}
		});

		List<Vendedor> result = new ArrayList<>();
		scoreMap.entrySet().stream().sorted(Map.Entry.comparingByValue((o1, o2) -> o2 - o1))
				.forEach(entry -> result.add(vendedorMap.get(entry.getKey())));

		return result;
	}
}
