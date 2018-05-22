package sge.regla;



public class Sensor {


	double medicion;
	double tiempoDeEspera;
	AvisoCambioSensor aviso=new AvisoCambioSensor();
	
	
	public Sensor(double _medicion, double _tiempoDeEspera) {
		medicion = _medicion;
		tiempoDeEspera = _tiempoDeEspera;
	}

	public void actualizarMedicion(int _medicion) {
		medicion = _medicion;
		aviso.notificar();
		// subscriptions.forEach(sub->sub.request(1));
	}

	public double getMedicion() {
		
		return medicion;
	}
	
	
	public void tomarMedicion() {
			// ponemos valor fijo de prueba
			medicion = 15;
			System.out.println("tiempo de espera es:" + " " + tiempoDeEspera);

	}

	
public void agregarObserver(CambiaPorElSensor objeto) {
	aviso.agregar(objeto);
}
	
}
