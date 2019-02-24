package sge.modelo.valueobjects;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import sge.dao.Persistible;

public abstract class DispositivoVO {

	protected String username;
	protected RestriccionHorasFamiliaVO restriccionHoras;
	protected String nombre;
	protected Double consumoPorHora;
	protected LocalDateTime instanteDeCreacion;
	protected Boolean bajoconsumo;

	public DispositivoVO() {
	}

	public DispositivoVO(String _nombre, Double _consumoPorHora, String _username, Boolean _bajoconsumo) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		username = _username;
		bajoconsumo = _bajoconsumo;
	}

	public DispositivoVO(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
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
		return 1.0;
	}

	public double mensualMinimoHoras() {
		return restriccionHoras.getMinimo();
	}

	public double mensualMaximoHoras() {
		return restriccionHoras.getMaximo();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getInstanteDeCreacion() {
		return instanteDeCreacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setConsumoPorHora(Double consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}

	public RestriccionHorasFamiliaVO getRestriccionHoras() {
		return restriccionHoras;
	}

	public void setRestriccionHoras(RestriccionHorasFamiliaVO restriccionHoras) {
		this.restriccionHoras = restriccionHoras;
	}

}
