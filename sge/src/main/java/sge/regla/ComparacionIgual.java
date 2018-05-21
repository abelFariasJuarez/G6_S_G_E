package sge.regla;

public class ComparacionIgual extends Comparador {

	public ComparacionIgual(double _valorEsperado, double _medicion) {
		super(_valorEsperado, _medicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean comparacion(double valorEsperado, double medicion) {
	return valorEsperado==medicion;
	}

}
