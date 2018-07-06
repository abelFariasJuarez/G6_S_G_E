package sge.dispositivo;

import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public class Dispositivo {

	public Dispositivo(String _nombre, Double _consumoPorHora,String _username) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		username=_username;
	}
	public Dispositivo() {
	};
	
	public void setInstanteDeCreacion(LocalDateTime _instanteDeCreacion) {
		instanteDeCreacion = _instanteDeCreacion;
	}
	protected String nombre;
	protected Double consumoPorHora;
	protected LocalDateTime instanteDeCreacion;
	@SerializedName("username")
	protected String username;
	public String getNombre() {
		return nombre;
	}
	public String getIdUserName() {
		return username;
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
