package sge.dispositivo;

import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

import sge.dispositivo.familia.RestriccionHorasFamilia;

public class Dispositivo {
	
	protected String nombre;
	protected Double consumoPorHora;
	protected LocalDateTime instanteDeCreacion;
	@SerializedName("username")
	protected String username;
	protected Boolean bajoconsumo;
	protected RestriccionHorasFamilia restriccionHoras;

	public Dispositivo(String _nombre, Double _consumoPorHora, String _username, Boolean _bajoconsumo) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		username = _username;
		bajoconsumo = _bajoconsumo;
	}

	// constructor prueba parte de dispositivos a cargar
	public Dispositivo(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		bajoconsumo = _bajoconsumo;
	}

	public void setInstanteDeCreacion(LocalDateTime _instanteDeCreacion) {
		instanteDeCreacion = _instanteDeCreacion;
	}

	public Boolean getBajoconsumo() {
		return bajoconsumo;
	}

	public void setBajoconsumo(Boolean bajoconsumo) {
		this.bajoconsumo = bajoconsumo;
	}

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

	public double mensualMinimoHoras() {
		return restriccionHoras.minimo();
	}

	public double mensualMaximoHoras() {
		return restriccionHoras.maximo();
	}

	public void restriccionHoras(RestriccionHorasFamilia h) {
		restriccionHoras = h;		
	}

}
