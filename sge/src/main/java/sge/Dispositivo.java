package sge;

public class Dispositivo {
	
	public Dispositivo(String nombre, Float consumoPorHora, boolean encendido) {
		this.nombre = nombre;
		this.consumoPorHora = consumoPorHora;
		this.encendido = encendido;
	}

	private String nombre;
	private Float consumoPorHora;
	private boolean encendido;
	private Float horasEncendido;
	
	
	public Float getHorasEncendido() {
		return horasEncendido;
	}

	public void setHorasEncendido(Float horasEncendido) {
		this.horasEncendido = horasEncendido;
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
		System.out.println("\t" + this.nombre + " " + this.consumoPorHora + "  " + this.encendido);

	}
	
	public Float informarConsumo(){
		return (this.getconsumoPorHora()*this.getHorasEncendido());
	}
}
