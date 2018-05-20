package sge;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispositivoInteligente extends Dispositivo implements IInteligente {

	private boolean encendido;
	public List<Sensor> sensores;
	public EstadoDispositivo estado;
	public List<Intervalo> intervalos = new ArrayList<Intervalo>();

	public DispositivoInteligente(String _nombre, Double _consumoPorHora, boolean _encendido) {
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

	public double consumo_ultimas_n_horas(double horas) {
		LocalDateTime instanteComienzo = LocalDateTime.now().minusMinutes((long) (horas * 60));
		return this.consumo_periodo(instanteComienzo, LocalDateTime.now());
	}

	public double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		double valueReturn = intervalos.stream().filter(i -> i.estoyDentroDePeriodo(instanteDesde, instanteHasta))
				.mapToDouble(i -> i.informarConsumo(this, instanteDesde, instanteHasta)).sum();

		return valueReturn;
	}

	@Override
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;

	}

	@Override
	public void setEstado(EstadoDispositivo _estado) {
		
		this.cerrarUltimoAbrirNuevoIntervalo(_estado);
		estado = _estado;

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

	@Override
	public boolean estoyON() {
		return encendido;
	}

	@Override
	public boolean estoyOFF() {
		return encendido == false;
	}

	@Override
	public void prender() {
		estado.prender(this);
	}

	@Override
	public void apagar() {
		estado.apagar(this);
	}

	@Override
	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);

	}

	@Override
	public void presentate() {
		super.presentate();
		System.out.println("Encendido:" + encendido);
	}

	public void addIntervalo(Intervalo inter) {
		intervalos.add(inter);
	}

}
