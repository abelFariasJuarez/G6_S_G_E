package sge.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.dao.Persistible;
import sge.modelo.valueobjects.ComparadorVO;
import sge.modelo.valueobjects.SensorVO;

@Entity
@Table(name = "Condicion")
public class Condicion extends Persistible {

	@Column(name = "valorEsperado")
	private double valorEsperado;
	@OneToOne(cascade = CascadeType.ALL)
	private SensorVO sensor;
	@OneToOne(cascade = CascadeType.ALL)
	private ComparadorVO comparador;

	public Condicion(SensorVO _sensor, ComparadorVO _comp, double _valorEsperado) {
		valorEsperado = _valorEsperado;
		sensor = _sensor;
		comparador = _comp;
	}

	public Condicion() {
	}

	public double getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(double valorEsperado) {
		this.valorEsperado = valorEsperado;
	}

	public SensorVO getSensor() {
		return sensor;
	}

	public void setSensor(SensorVO sensor) {
		this.sensor = sensor;
	}

	public ComparadorVO getComparador() {
		return comparador;
	}

	public void setComparador(ComparadorVO comparador) {
		this.comparador = comparador;
	}

}
