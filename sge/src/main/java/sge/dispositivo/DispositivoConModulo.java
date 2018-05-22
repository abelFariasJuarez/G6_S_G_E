package sge.dispositivo;

import java.time.LocalDateTime;

public class DispositivoConModulo extends Inteligente {
	private static Integer puntos = 10;
	private DispositivoEstandar dispo;


	// decorator|adapter
	public DispositivoConModulo(DispositivoEstandar _dis, boolean _encendido) {
		super("", 0.0, _encendido);
		dispo = _dis;
	}

	@Override
	public String getNombre() {
		return dispo.getNombre();
	}

	@Override
	public Double getConsumoPorHora() {
		return dispo.getConsumoPorHora();
	}

	public void presentate() {
		System.out.println("\t" + this.getNombre() + " " + this.getConsumoPorHora() + "  ");
	}

	public DispositivoEstandar getEstandar() {
		return dispo;
	}

	public void setEstandar(DispositivoEstandar dispo) {
		this.dispo = dispo;
	}

	public static Integer puntos() {
		return puntos;
	}
	



	public Double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return super.consumo_periodo(instanteDeCreacion, instanteHasta) + dispo.consumo_periodo(instanteDesde, instanteDeCreacion);
	}
}
