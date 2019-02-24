package sge.modelo.valueobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.dao.IPersistible;
import sge.modelo.RegistroSensores;

public class SensorVO extends Observable {

	protected Long oid;
	private double medicion;
	private double tiempoDeEspera;
	private String nombre;
	private List<MedicionLogVO> mediciones = new ArrayList<MedicionLogVO>();

	public String toString() {
		return this.getClass().getSimpleName();
	}
		
	public SensorVO() {
		super();
	}

	public void activar() {
		long tiempoEspera = (long) tiempoDeEspera;

		Date ahora = new Date();

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				// tomarMedicion();
				actualizarMedicion();
				System.out.println("sensor se activa");
				System.out.println("sensor" + nombre);
				//aviso.notificar();
				
			}

		};

		timer.schedule(task, ahora, tiempoEspera);
		// (task, esteInstante , tiempoEspera);
		System.out.println("Hora de activacion Sensor " + nombre + ": " + ahora);

	}

	public SensorVO(double _medicion, double _tiempoDeEspera, String _nombre) {
		super();
		medicion = _medicion;
		tiempoDeEspera = _tiempoDeEspera;
		nombre = _nombre;
		this.addObserver(RegistroSensores.getInstance());
	}

	public void actualizarMedicion() {
		  this.notifyObservers();	// subscriptions.forEach(sub->sub.request(1));
	}

	public double getMedicion() {

		return medicion;
	}

	public void setMedicion(double _medicion) {
		this.loggearMedicion(_medicion);
		medicion = _medicion;
		this.actualizarMedicion();
	}

	private void loggearMedicion(double _medicion) {
		mediciones.add(new MedicionLogVO(_medicion));		
	}

	public void tomarMedicion() {
		// ponemos valor fijo de prueba
		medicion = 15;
		System.out.println("tiempo de espera es:" + " " + tiempoDeEspera);

	}

	public double getTiempoDeEspera() {
		return tiempoDeEspera;
	}

	public void setTiempoDeEspera(double tiempoDeEspera) {
		this.tiempoDeEspera = tiempoDeEspera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
