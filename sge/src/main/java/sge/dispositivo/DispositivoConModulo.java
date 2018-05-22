package sge.dispositivo;

import java.time.LocalDateTime;

public class DispositivoConModulo extends Inteligente {
	private static Integer puntos = 10;
	private DispositivoEstandar dispo;
	private LocalDateTime instanteDeTransformacion;

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

	public DispositivoEstandar getDispo() {
		return dispo;
	}

	public void setDispo(DispositivoEstandar dispo) {
		this.dispo = dispo;
	}

	public static Integer puntos() {
		return puntos;
	}
	
	public void setInstanteDeTransformacion(LocalDateTime _instanteDeTransformacion) {
		instanteDeTransformacion = _instanteDeTransformacion;
	}

	public double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return super.consumo_periodo(instanteDeTransformacion, instanteHasta) + dispo.consumo_periodo(instanteDesde, instanteDeTransformacion);
	}
}
