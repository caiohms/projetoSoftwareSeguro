package model;

import lombok.Data;

import java.util.Date;

@Data
public class HistoricoBusca {
	private Integer id;
	private Integer comprador;
	private Date datetime;
	private String busca;
}
