package sge;

public  class Dispositivo {

	public Dispositivo(String _nombre, Float _consumoPorHora) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
	}
	public Dispositivo() {
	};
	

	private String nombre;
	private Float consumoPorHora;

	public String getNombre() {
		return nombre;
	}

	public Float getConsumoPorHora() {
		return consumoPorHora;
	}

	public void presentate() {
		System.out.println("\t" + nombre + " " + consumoPorHora + "  ");

	}

	public Float informarConsumo() {
		return this.getConsumoPorHora();
	}

}
