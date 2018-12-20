package sge.modelo.dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.driver.DriverBasico;
import sge.modelo.regla.RegistroDispositivos;
import sge.modelo.regla.RegistroSensores;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;

@Entity
@Table(name = "Inteligente")
public abstract class Inteligente extends Dispositivo {

	@Transient
	private DriverBasico driver;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy="inteligente")
	private List<Regla> reglas = new ArrayList<Regla>();
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Sensor> sensores = new ArrayList<Sensor>();	
	@Column(name = "encendido")
	private boolean encendido;
	@OneToOne(cascade = CascadeType.ALL)
	private EstadoDispositivo estado;
	//@Column(name = "horasEncendido")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Intervalo> intervalos = new ArrayList<Intervalo>();

	public List<Regla> getReglas() {
		return reglas;
	}

	public void setReglas(List<Regla> reglas) {
		this.reglas = reglas;
	}

	public Inteligente() {
		super();
		this.setDriver(new DriverBasico());
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void agregarSensor(Sensor sensor) {
		sensores.add(sensor);
		RegistroSensores.getInstance().registrarSensor(sensor, this);
	}

	public Inteligente(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido, DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo);
		// para que se genere el primer intervalo prendido, primero lo apago y despues
		// lo prendo afarias
		if (_encendido) {
			estado = new EstadoApagado();
			this.prender();
		} else {
			estado = new EstadoPrendido();
			this.apagar();
		}
		this.setDriver(_driver);
		RegistroDispositivos.getInstance().registrarDispositivo(this);
	}

	public Inteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo, DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		this.setDriver(_driver);
		RegistroDispositivos.getInstance().registrarDispositivo(this);

	}

	public void setIntervalo(List<Intervalo> intervalo) {
		intervalos = intervalo;
	}

	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}

	public void setEstado(EstadoDispositivo _estado) {
		this.cerrarUltimoAbrirNuevoIntervalo(_estado);
		estado = _estado;
	}

	public boolean estoyON() {
		return encendido;
	}

	public boolean estoyOFF() {
		return encendido == false;
	}

	public void prender() {
		if (estado == null) {
			estado = new EstadoApagado();
		}
		estado.prender(this);
	}

	public void apagar() {
		estado.apagar(this);
	}

	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);
	}

	public double consumo_ultimas_n_horas(double horas) {
		LocalDateTime instanteComienzo = LocalDateTime.now().minusMinutes((long) (horas * 60));
		return this.consumo_periodo(instanteComienzo, LocalDateTime.now());
	}

	public Double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		double valueReturn = intervalos.stream()
				.filter(i -> i.estoyDentroDePeriodo(instanteDesde, instanteHasta)
						|| i.periodoEstaDentroDeMi(instanteDesde, instanteHasta))
				.mapToDouble(i -> i.informarConsumo(this, instanteDesde, instanteHasta)).sum();

		return valueReturn;
	}

	private void cerrarUltimoAbrirNuevoIntervalo(EstadoDispositivo _estado) {
		LocalDateTime esteInstante = LocalDateTime.now();

		if (!intervalos.isEmpty()) {
			Intervalo ultimoIntervalo = intervalos.get(intervalos.size() - 1);
			ultimoIntervalo.setFin(esteInstante);
		}

		Intervalo nuevoIntervalo = new Intervalo();
		nuevoIntervalo.setInicio(esteInstante);
		nuevoIntervalo.setEstado(_estado);

		this.addIntervalo(nuevoIntervalo);
	}

	private void addIntervalo(Intervalo inter) {
		intervalos.add(inter);
	}

	@Override
	public void presentate() {
		System.out.println("\t" + nombre + " " + consumoPorHora + "  " + encendido);
	}

	@Override
	public Double informarConsumo() {
		return super.getConsumoPorHora() * estado.factor();
	}

	public Double consumoActual() {
		return consumoPorHora * estado.factor();
	}

	public DriverBasico getDriver() {
		return driver;
	}

	public void setDriver(DriverBasico driver) {
		this.driver = driver;
		driver.setMiDispositivo(this);
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
	}

	public List<Intervalo> getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(List<Intervalo> intervalos) {
		this.intervalos = intervalos;
	}

	public EstadoDispositivo getEstado() {
		return estado;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public void mostrarIntervalosEncendidos() {
		this.getIntervalos().stream().filter(i -> i.estoyEncendido()).forEach(i -> i.presentate());
	}

}
