package model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class Vendedor extends Usuario {
	String nome;
}
