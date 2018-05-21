package sge.regla.comparador;

public class ComparacionMenor extends Comparador{

	public ComparacionMenor(double _valorEsperado, double _medicion) {
		super(_valorEsperado, _medicion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean comparacion(double valorEsperado2, double medicion) {
		return valorEsperado>medicion;
	}

}
