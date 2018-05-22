package sge.regla;

public class ComparacionMayor extends Comparador {

	public ComparacionMayor(double _valorEsperado, double _medicion) {
		super(_valorEsperado, _medicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean comparacion(double valorEsperado, double medicion) {
	return valorEsperado<medicion;
	}

}
