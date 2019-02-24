package sge.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.dao.IPersistible;

@Entity
@Table(name="Sensor")
public class Sensor extends Persistible {

	@Column(name = "medicion")
	private double medicion;
	@Column(name = "tiempoDeEspera")
	private double tiempoDeEspera;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "mediciones")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<MedicionLog> mediciones = new ArrayList<MedicionLog>();
	
	public String toString() {
		return getOid() + "-" + this.getClass().getSimpleName();
	}
		
	public Sensor() {
		super();
	}

	public Sensor(double _medicion, double _tiempoDeEspera, String _nombre) {
		super();
		medicion = _medicion;
		tiempoDeEspera = _tiempoDeEspera;
		nombre = _nombre;
	}

	public double getMedicion() {
		return medicion;
	}

	public void setMedicion(double _medicion) {
		medicion = _medicion;
	}

	public double getTiempoDeEspera() {
		return tiempoDeEspera;
	}

	public void setTiempoDeEspera(double tiempoDeEspera) {
		this.tiempoDeEspera = tiempoDeEspera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

}
