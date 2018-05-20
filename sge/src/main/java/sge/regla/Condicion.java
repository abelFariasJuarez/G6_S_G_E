package sge.regla;

public class Condicion {

	private double valorEsperado;
	Sensor sensor;
	
	public boolean verificar() {
		return this.comparacion(valorEsperado,sensor.getMedicion());
	}
	private boolean comparacion(double valorEsperado2, double medicion) {
		
		return  valorEsperado==medicion;
	}


}
