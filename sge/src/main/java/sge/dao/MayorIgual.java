package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.ComparadorVO;

@Entity
@DiscriminatorValue("GE")
@Table(name="MayorIgual")
public class MayorIgual extends Comparador {
	public MayorIgual() {
		super();
	}
}
