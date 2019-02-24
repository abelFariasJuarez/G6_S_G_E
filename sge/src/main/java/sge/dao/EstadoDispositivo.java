package sge.dao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.dao.Persistible;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="stateType") 
@Table(name = "EstadoDispositivo")
public abstract class EstadoDispositivo extends Persistible{

	@Column(name = "factor")
	double factor;

	public double factor() {
		return factor;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

}
