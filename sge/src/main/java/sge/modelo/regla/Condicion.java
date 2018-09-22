package sge.modelo.regla;

import sge.modelo.regla.comparador.Comparador;

public class Condicion {

	private double valorEsperado;
	private Sensor sensor;
	private Comparador comp;

	public Condicion(Sensor _sensor, Comparador _comp, double _valorEsperado) {
		valorEsperado = _valorEsperado;
		sensor = _sensor;
		comp = _comp;
	}

	public boolean meCumplo() {
		return comp.comparar(sensor.getMedicion(), valorEsperado);
	}

}
