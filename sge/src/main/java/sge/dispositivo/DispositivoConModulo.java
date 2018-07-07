package sge.dispositivo;

import java.time.LocalDateTime;

public class DispositivoConModulo extends Inteligente {
	
	
	public DispositivoConModulo(DispositivoEstandar _dis) {
		super("", 0.0, _dis.bajoconsumo);
		// TODO Auto-generated constructor stub
		dispo = _dis;
	}




	private static Integer puntos = 10;
	private DispositivoEstandar dispo;


	// decorator|adapter
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido,String _idUserName) {
		super("", 0.0,"",_dis.bajoconsumo, _encendido);
		dispo = _dis;
	}
	
	
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido) {
		super("", 0.0,"",_dis.bajoconsumo, _encendido);
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
