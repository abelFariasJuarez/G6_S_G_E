package sge;

public class Dispositivo {
	Integer nombre;
	Integer consumoPorHora;
	boolean encedido;

	public boolean estoyON() {
		return this.encedido;
	}
}
