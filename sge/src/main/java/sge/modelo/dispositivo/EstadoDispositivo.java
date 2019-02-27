package sge.modelo.dispositivo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import sge.modelo.Persistible;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="stateType") 
@Table(name = "EstadoDispositivo")
public abstract class EstadoDispositivo extends Persistible{

	@Transient
	@SerializedName("type")
	private String typeName;
	
	@Column(name = "factor")
	double factor;

	public EstadoDispositivo() {
	    typeName = getClass().getName();
	}
	
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

	public double getFactor() {
		return factor;
	}

	public void setFactor(double factor) {
		this.factor = factor;
	}

}
