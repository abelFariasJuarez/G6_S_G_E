package sge.modelo.regla.comparador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("GE")
@Table(name="MayorIgual")
public class MayorIgual extends Comparador {
	public MayorIgual() {
		super();
		cmp = (v1, v2) -> v1 >= v2;
	}
}
