package sge.dispositivo;

public class DispositivoInteligente extends Inteligente {
	private static Integer puntos = 15;
	
	public DispositivoInteligente(String _nombre, Double _consumoPorHora, boolean _encendido) {
		super(_nombre, _consumoPorHora, _encendido);
	}
	
	public static Integer puntos() {
		return puntos;
	}
}
