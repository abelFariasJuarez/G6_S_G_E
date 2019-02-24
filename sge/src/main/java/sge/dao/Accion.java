package sge.dao;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import sge.dao.Persistible;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actionType")
@Table(name = "Accion")

public abstract class Accion extends Persistible {
	public Accion() {
		super();
	}
}