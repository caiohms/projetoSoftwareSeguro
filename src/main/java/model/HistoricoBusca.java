package model;

import lombok.Data;

import java.util.Date;

@Data
public class HistoricoBusca {
	private final Integer id;
	private final Integer comprador;
	private final String busca;
	private final Date datetime;
}
