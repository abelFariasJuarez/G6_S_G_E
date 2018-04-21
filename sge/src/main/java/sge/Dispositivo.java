package sge;

public class Dispositivo {
	public Dispositivo(String nombre, Integer consumoPorHora, boolean encedido) {
		super();
		this.nombre = nombre;
		this.consumoPorHora = consumoPorHora;
		this.encedido = encedido;
	}

	String nombre;
	Integer consumoPorHora;
	boolean encedido;

	
	
	public boolean estoyON() {
		return this.encedido;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	
}
