package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class Adm extends Usuario implements Serializable {

	private int id;

	public Adm() { /* empty constructor */}
}
