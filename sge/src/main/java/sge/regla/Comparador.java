package sge.regla;

public abstract class Comparador {
	protected double valorEsperado;
	protected double medicion;
	public Comparador(double _valorEsperado,double _medicion) {
		valorEsperado=_valorEsperado;
				medicion=_medicion;
	}
	public abstract boolean comparacion(double valorEsperado2, double medicion);
}
