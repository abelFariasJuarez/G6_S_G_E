package sge.dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sge.driver.DriverBasico;
import sge.driver.RegistroDispositivos;
import sge.driver.RegistroSensores;
import sge.regla.Sensor;

public abstract class Inteligente extends Dispositivo {

	public boolean encendido;
	public List<Sensor> sensores= new ArrayList<Sensor>();
	public EstadoDispositivo estado;
	public List<Intervalo> intervalos = new ArrayList<Intervalo>();
	public DriverBasico driver;
	
	
	
	public List<Sensor> getSensores() {
		return sensores;
	}
	
	public void agregarSensor(Sensor sensor) {
		sensores.add(sensor);
		RegistroSensores.getInstance().registrarSensor(sensor, this);
	}

	public Inteligente(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido,DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo);
		// para que se genere el primer intervalo prendido, primero lo apago y despues
		// lo prendo afarias
		driver=_driver;
		if (_encendido) {
			estado = new EstadoApagado();
			this.prender();
		} else {
			estado = new EstadoPrendido();
			this.apagar();
		}
		
		RegistroDispositivos.getInstance().registrarDispositivo(this);
	}

	public Inteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo,DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		driver=_driver;
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
		driver.apagar(this);
	}

	public void apagar() {
		estado.apagar(this);
		driver.apagar(this);
	}

	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);
		driver.ahorroDeEnergia(this);
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
	}
}
