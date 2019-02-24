package sge.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import sge.modelo.DriverBasico;

@Entity
@Table(name = "Inteligente")
public abstract class Inteligente extends Dispositivo {

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Regla> reglas = new ArrayList<Regla>();
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Sensor> sensores = new ArrayList<Sensor>();	
	@Column(name = "encendido")
	private boolean encendido;
	@OneToOne(cascade = CascadeType.ALL)
	private EstadoDispositivo estado;
	//@Column(name = "horasEncendido")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Intervalo> intervalos = new ArrayList<Intervalo>();
	
	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

	public Inteligente() {
		super();
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void agregarSensor(Sensor sensor) {
		sensores.add(sensor);
	}

	public Inteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
	}

	public Inteligente(String string, double d, String string2, Boolean bajoconsumo, Boolean _encendido) {
		// TODO Auto-generated constructor stub
	}

	public Inteligente(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo) {
		// TODO Auto-generated constructor stub
	}

	public void setIntervalo(List<Intervalo> intervalo) {
		intervalos = intervalo;
	}

	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}

	public void setEstado(EstadoDispositivo _estado) {
		estado = _estado;
	}

	private void addIntervalo(Intervalo inter) {
		intervalos.add(inter);
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	public List<Intervalo> getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(List<Intervalo> intervalos) {
		this.intervalos = intervalos;
	}

	public EstadoDispositivo getEstado() {
		return estado;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

}
