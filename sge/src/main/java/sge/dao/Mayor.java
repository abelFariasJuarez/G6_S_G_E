package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.ComparadorVO;

@Entity
@DiscriminatorValue("GT")
@Table(name="Mayor")
public class Mayor extends Comparador{

	public Mayor() {
		super();
	}
	
}
