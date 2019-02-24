package sge.modelo.valueobjects;

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

import sge.modelo.DriverBasico;
import sge.modelo.RegistroDispositivos;
import sge.modelo.RegistroSensores;

public abstract class InteligenteVO extends DispositivoVO {

	private DriverBasico driver;
	private List<ReglaVO> reglas = new ArrayList<ReglaVO>();
	private List<SensorVO> sensores = new ArrayList<SensorVO>();	
	private boolean encendido;
	private EstadoDispositivoVO estado;
	//@Column(name = "horasEncendido")
	private List<IntervaloVO> intervalos = new ArrayList<IntervaloVO>();
	
	
	public String getNombreEstado() {
		return estado.getClass().getSimpleName();
	}

	public List<ReglaVO> getReglas() {
		return reglas;
	}

	public void setReglas(List<ReglaVO> reglas) {
		this.reglas = reglas;
	}

	public InteligenteVO() {
		super();
		this.setDriver(new DriverBasico());
	}

	public List<SensorVO> getSensores() {
		return sensores;
	}

	public void agregarSensor(SensorVO sensor) {
		sensores.add(sensor);
		RegistroSensores.getInstance().registrarSensor(sensor, this);
	}

	public InteligenteVO(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido, DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo);
		// para que se genere el primer intervalo prendido, primero lo apago y despues
		// lo prendo afarias
		if (_encendido) {
			estado = new EstadoApagadoVO();
			this.prender();
		} else {
			estado = new EstadoPrendidoVO();
			this.apagar();
		}
		this.setDriver(_driver);
		RegistroDispositivos.getInstance().registrarDispositivo(this);
	}

	public InteligenteVO(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo, DriverBasico _driver) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		this.setDriver(_driver);
		RegistroDispositivos.getInstance().registrarDispositivo(this);

	}

	public void setIntervalo(List<IntervaloVO> intervalo) {
		intervalos = intervalo;
	}

	public void setEncendido(Boolean encendido) {
		this.encendido = encendido;
	}

	public void setEstado(EstadoDispositivoVO _estado) {
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
			estado = new EstadoApagadoVO();
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

	private void cerrarUltimoAbrirNuevoIntervalo(EstadoDispositivoVO _estado) {
		LocalDateTime esteInstante = LocalDateTime.now();

		if (!intervalos.isEmpty()) {
			IntervaloVO ultimoIntervalo = intervalos.get(intervalos.size() - 1);
			ultimoIntervalo.setFin(esteInstante);
		}

		IntervaloVO nuevoIntervalo = new IntervaloVO();
		nuevoIntervalo.setInicio(esteInstante);
		nuevoIntervalo.setEstado(_estado);

		this.addIntervalo(nuevoIntervalo);
	}

	private void addIntervalo(IntervaloVO inter) {
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

	public List<IntervaloVO> getIntervalos() {
		return intervalos;
	}

	public void setIntervalos(List<IntervaloVO> intervalos) {
		this.intervalos = intervalos;
	}

	public EstadoDispositivoVO getEstado() {
		return estado;
	}

	public void setSensores(List<SensorVO> sensores) {
		this.sensores = sensores;
	}

	public void mostrarIntervalosEncendidos() {
		this.getIntervalos().stream().filter(i -> i.estoyEncendido()).forEach(i -> i.presentate());
	}

}
