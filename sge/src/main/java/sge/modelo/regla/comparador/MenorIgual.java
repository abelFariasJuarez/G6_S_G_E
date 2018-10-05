package sge.modelo.regla.comparador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("LE")
@Table(name="MenorIgual")
public class MenorIgual extends Comparador {
	public MenorIgual() {
		super();
		cmp = (v1, v2) -> v1 <= v2;
	}
}
