package sge;

public class Dispositivo {

	public Dispositivo(String _nombre, Float _consumoPorHora, boolean _encendido) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		encendido = _encendido;
	}

	private String nombre;
	private Float consumoPorHora;
	private boolean encendido;
	private Float horasEncendido;

	public Float getHorasEncendido() {
		return horasEncendido;
	}

	public void setHorasEncendido(Float _horasEncendido) {
		horasEncendido = _horasEncendido;
	}

	public boolean estoyON() {
		return encendido;
	}

	public String getNombre() {
		return nombre;
	}

	public Float getconsumoPorHora() {
		return consumoPorHora;
	}

	public void presentate() {
		System.out.println("\t" + nombre + " " + consumoPorHora + "  " + encendido);

	}

	public Float informarConsumo() {
		return (this.getconsumoPorHora() * this.getHorasEncendido());
	}
}
