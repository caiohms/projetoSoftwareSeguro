package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Comprador extends Usuario {
	private String email;
	private String senha;

	public Comprador() { /* empty constructor */ }

}

