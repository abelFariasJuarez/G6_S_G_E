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
		encendido = _encendido;
		if (encendido == true) {
			this.setEstado(new EstadoPrendido());
		} else {
			this.setEstado(new EstadoApagado());
		}

	}

	public double consumo_ultimas_n_horas(double horas) {
		LocalDateTime instanteComienzo = LocalDateTime.now().minusMinutes((long) (horas * 60));
		return this.consumo_periodo(instanteComienzo,LocalDateTime.now());
	}

	public double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return intervalos.stream().filter(i -> i.dentroDePeriodo(instanteDesde,instanteHasta))
				.mapToDouble(i -> i.informarConsumo(this, instanteDesde,instanteHasta)).sum();
	}

	@Override
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;

	}

	@Override
	public void setEstado(EstadoDispositivo _estado) {
		estado = _estado;
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
