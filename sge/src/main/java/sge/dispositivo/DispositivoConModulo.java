package sge.dispositivo;

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

	public DispositivoEstandar getDispo() {
		return dispo;
	}

	public void setDispo(DispositivoEstandar dispo) {
		this.dispo = dispo;
	}

	public static Integer puntos() {
		return puntos;
	}
}
