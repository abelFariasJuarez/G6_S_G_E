package sge;

public class Dispositivo {
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
