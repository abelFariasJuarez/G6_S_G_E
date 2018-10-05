package sge.modelo.regla.comparador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("GT")
@Table(name="Mayor")
public class Mayor extends Comparador {

	public Mayor() {
		super();
		cmp = (v1, v2) -> v1 > v2;
	}
	
}
