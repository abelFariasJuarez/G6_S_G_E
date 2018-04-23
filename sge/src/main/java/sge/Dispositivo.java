package sge;

public class Dispositivo {
	public Dispositivo(String nombre, Integer consumoPorHora, boolean encendido) {
		super();
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
	
	
	public void mostrarDispositivo() {
	System.out.println("\t"+ this.nombre + " " + this.toString());
	
	
	
}
}
