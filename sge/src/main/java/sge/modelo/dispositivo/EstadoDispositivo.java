package sge.modelo.dispositivo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.Persistible;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="stateType") 
@Table(name = "EstadoDispositivo")
public abstract class EstadoDispositivo extends Persistible{

	@Transient 
	double factor;

	public void prender(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public void apagar(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(false);
	}

	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public double factor() {
		return factor;
	}

	public double consumoFinal(double consumoPorHora) {
		double value = factor * consumoPorHora;
		return value;
	}

}
