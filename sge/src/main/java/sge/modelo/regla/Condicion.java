package sge.modelo.regla;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.modelo.regla.comparador.Comparador;

@Entity
@Table(name = "Condicion")
public class Condicion extends Persistible {

	@Column(name = "valorEsperado")
	private double valorEsperado;
	@OneToOne(cascade = CascadeType.ALL)
	private Sensor sensor;
	@OneToOne(cascade = CascadeType.ALL)
	private Comparador comparador;

	public Condicion(Sensor _sensor, Comparador _comp, double _valorEsperado) {
		valorEsperado = _valorEsperado;
		sensor = _sensor;
		comparador = _comp;
	}

	public Condicion() {
	}

	public boolean meCumplo() {
		return comparador.comparar(sensor.getMedicion(), valorEsperado);
	}

	public double getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(double valorEsperado) {
		this.valorEsperado = valorEsperado;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

}
