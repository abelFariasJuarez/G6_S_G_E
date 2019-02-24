package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("LT")
@Table(name="MenorIgual")
public class MenorIgual extends Comparador {
	public MenorIgual() {
		super();
	}
}
