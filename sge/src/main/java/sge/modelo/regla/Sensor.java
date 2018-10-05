package sge.modelo.regla;

import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import sge.modelo.IPersistible;

@Entity
@Table(name="Sensor")
public class Sensor extends Observable implements IPersistible {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oid")
	protected Long oid;

	@Column(name = "medicion")
	private double medicion;
	@Column(name = "tiempoDeEspera")
	private double tiempoDeEspera;
	@Column(name = "nombre")
	private String nombre;
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long id) {
		this.oid = id;
	}

	public String toString() {
		return getOid() + "-" + this.getClass().getSimpleName();
	}
		
	public Sensor() {
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

	public Sensor(double _medicion, double _tiempoDeEspera, String _nombre) {
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

		medicion = _medicion;
		this.actualizarMedicion();
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
