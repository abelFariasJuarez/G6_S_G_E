package sge.dao;

import java.util.function.BiFunction;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.dao.Persistible;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "comparatorType")
@Table(name = "Comparador")
public abstract class Comparador extends Persistible{
	public Comparador() {
		super();
	}
	
	
}
