package sge.dao;

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
import sge.modelo.valueobjects.RestriccionHorasFamiliaVO;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "deviceType")
@Table(name = "Dispositivo")
public abstract class Dispositivo extends Persistible {

	@SerializedName("username")
	@ManyToOne(cascade = CascadeType.MERGE)
	protected RestriccionHorasFamiliaVO restriccionHoras;
	@Column(name = "nombre")
	protected String nombre;
	@Column(name = "consumoPorHora")
	protected Double consumoPorHora;
	@Column(name = "instanteDeCreacion")
	@Convert(converter = LocalDateTimeConverter.class)
	protected LocalDateTime instanteDeCreacion;
	@Column(name = "bajoconsumo")
	protected Boolean bajoconsumo;

	public Dispositivo() {
	}

	public Dispositivo(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		nombre = _nombre;
		consumoPorHora = _consumoPorHora;
		bajoconsumo = _bajoconsumo;
	}

	public Dispositivo(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo) {
		// TODO Auto-generated constructor stub
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

	public Double getConsumoPorHora() {
		return consumoPorHora;
	}

	public void presentate() {
		System.out.println("\t" + nombre + " " + consumoPorHora + "  ");
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
