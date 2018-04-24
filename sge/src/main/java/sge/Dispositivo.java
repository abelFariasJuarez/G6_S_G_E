package sge;

public class Dispositivo {
	
	public Dispositivo(String nombre, Integer consumoPorHora, boolean encendido) {
		this.nombre = nombre;
		this.consumoPorHora = consumoPorHora;
		this.encendido = encendido;
	}

	private String nombre;
	private Integer consumoPorHora;
	private boolean encendido;

	public boolean estoyON() {
		return encendido;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getconsumoPorHora() {
		return consumoPorHora;
	}

	public void presentate() {
		System.out.println("\t" + this.nombre + " " + this.consumoPorHora + "  " + this.encendido);

	}
}
