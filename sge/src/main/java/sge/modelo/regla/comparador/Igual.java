package sge.modelo.regla.comparador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("EQ")
@Table(name="Igual")
public class Igual extends Comparador {

	public Igual() {
		super();
		cmp = (v1, v2) -> v1 == v2;
	}

}
