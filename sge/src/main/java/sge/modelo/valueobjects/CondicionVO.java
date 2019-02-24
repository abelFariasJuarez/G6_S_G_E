package sge.modelo.valueobjects;

public class CondicionVO {

	private double valorEsperado;
	private SensorVO sensor;
	private ComparadorVO comparador;

	public CondicionVO(SensorVO _sensor, ComparadorVO _comp, double _valorEsperado) {
		valorEsperado = _valorEsperado;
		sensor = _sensor;
		comparador = _comp;
	}

	public CondicionVO() {
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
