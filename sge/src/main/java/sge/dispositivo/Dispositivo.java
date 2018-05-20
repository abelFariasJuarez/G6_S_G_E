package sge.dispositivo;

public  class Dispositivo {

	public Dispositivo(String _nombre, Double _consumoPorHora) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
	}
	public Dispositivo() {
	};
	

	private String nombre;
	private Double consumoPorHora;

	public String getNombre() {
		return nombre;
	}

	public Double getConsumoPorHora() {
		return consumoPorHora;
	}

	public void presentate() {
		System.out.println("\t" + nombre + " " + consumoPorHora + "  ");

	}

	public Double informarConsumo() {
		return this.getConsumoPorHora();
	}

}
