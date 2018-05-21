package sge.regla;

public class Condicion {

	private double valorEsperado;
	private Sensor sensor;
	private Comparador comp;
	
	public Condicion(double _valorEsperado,Sensor _sensor,Comparador _comp ) {
		valorEsperado=_valorEsperado;
		sensor=_sensor;
		comp=_comp;
	}
	public boolean verificar() {
		return this.comparacion(valorEsperado,sensor.getMedicion());
	}
	private boolean comparacion(double valorEsperado, double medicion) {
		
		return  comp.comparacion(valorEsperado, medicion);
	}


}
