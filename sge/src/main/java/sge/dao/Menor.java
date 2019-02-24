package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.ComparadorVO;

@Entity
@DiscriminatorValue("LT")
@Table(name="Menor")
public class Menor extends Comparador {
	
	public Menor() {
		super();
	}
	
}
