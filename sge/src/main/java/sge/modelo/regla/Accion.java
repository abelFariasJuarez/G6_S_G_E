package sge.modelo.regla;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.modelo.dispositivo.Inteligente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actionType")
@Table(name = "Accion")

public abstract class Accion extends Persistible {
	public Accion() {
		super();
	}

	public void ejecutar(Inteligente dispo) {

	}

}