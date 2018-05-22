package sge.dispositivo;

import java.time.LocalDateTime;

public  class Dispositivo {

	public Dispositivo(String _nombre, Double _consumoPorHora) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
	}
	public Dispositivo() {
	};
	
	public void setInstanteDeCreacion(LocalDateTime _instanteDeCreacion) {
		instanteDeCreacion = _instanteDeCreacion;
	}
	protected String nombre;
	protected Double consumoPorHora;
	protected LocalDateTime instanteDeCreacion;
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
	
	public Double consumo_periodo(LocalDateTime inicioPeriodo, LocalDateTime finPeriodo) {
		return 0.0;
	}

}
