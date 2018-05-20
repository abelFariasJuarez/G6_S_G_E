package sge.dispositivo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sge.regla.Sensor;

public abstract class Inteligente extends Dispositivo {

	private boolean encendido;
	public List<Sensor> sensores;
	public EstadoDispositivo estado;
	public List<Intervalo> intervalos = new ArrayList<Intervalo>();
	
	public Inteligente(String _nombre, Double _consumoPorHora, boolean _encendido) {
		super(_nombre, _consumoPorHora);
		// para que se genere el primer intervalo prendido, primero lo apago y despues lo prendo afarias
		if (_encendido) {
			estado = new EstadoApagado();
			this.prender();
		} else {
			estado = new EstadoPrendido();
			this.apagar();
		}
	}
	
	public void setEncendido(boolean encendido) {
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

	public double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		double valueReturn = intervalos.stream().filter(i -> i.estoyDentroDePeriodo(instanteDesde, instanteHasta))
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
}
