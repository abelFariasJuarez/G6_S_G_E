package sge.modelo.valueobjects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.dao.Persistible;

public abstract class EstadoDispositivoVO {
	
	double factor;

	public void prender(InteligenteVO dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public void apagar(InteligenteVO dispositivoInteligente) {
		dispositivoInteligente.setEncendido(false);
	}

	public void ahorroDeEnergia(InteligenteVO dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public double factor() {
		return factor;
	}

	public double consumoFinal(double consumoPorHora) {
		double value = factor * consumoPorHora;
		return value;
	}

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

}
