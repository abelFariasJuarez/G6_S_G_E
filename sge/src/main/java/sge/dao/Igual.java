package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.ComparadorVO;

@Entity
@DiscriminatorValue("EQ")
@Table(name="Igual")
public class Igual extends Comparador {

	public Igual() {
		super();
	}

}
