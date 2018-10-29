package sge.modelo.reporte;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actionType")
@Table(name = "Reporte")

public abstract class Reporte extends Persistible {
	public Reporte() {}
	
	public abstract void generarReporte();
	public abstract void mostrarReporte();
	
}
