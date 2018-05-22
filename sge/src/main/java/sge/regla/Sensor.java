package sge.regla;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Sensor {

	double medicion;
	double tiempoDeEspera;
	String nombre;
	AvisoCambioSensor aviso = new AvisoCambioSensor();

	public void activarSensor() {
		long tiempoEspera = (long) tiempoDeEspera;

		Date ahora = new Date();

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				// tomarMedicion();
				actualizarMedicion();
				System.out.println("sensor" + nombre);
				// aviso.notificar();
			}

		};

		timer.schedule(task, ahora, tiempoEspera);
		// (task, esteInstante , tiempoEspera);
		System.out.println("Hora de activacion Sensor " + nombre + ": " + ahora);

	}

	public Sensor(double _medicion, double _tiempoDeEspera, String _nombre) {
		medicion = _medicion;
		tiempoDeEspera = _tiempoDeEspera;
		nombre = _nombre;
	}

	public void actualizarMedicion() {

		aviso.notificar();
		// subscriptions.forEach(sub->sub.request(1));
	}

	public double getMedicion() {

		return medicion;
	}

	public void setMedicion(double _medicion) {

		medicion = _medicion;
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
