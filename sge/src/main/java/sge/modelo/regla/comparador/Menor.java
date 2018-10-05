package sge.modelo.regla.comparador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("LT")
@Table(name="Menor")
public class Menor extends Comparador {
	
	public Menor() {
		super();
		cmp = (v1, v2) -> v1 < v2;
	}
	
}
