package sge.modelo.dispositivo;

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

import sge.modelo.Persistible;
import sge.repositorios.LocalDateTimeConverter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "deviceType")
@Table(name = "Dispositivo")
public abstract class Dispositivo extends Persistible {

	@Transient
	@SerializedName("typeName")
	private String typeName;
	@SerializedName("username")
	@Transient
	protected String username;
	@ManyToOne(cascade = CascadeType.MERGE)
	protected RestriccionHorasFamilia restriccionHoras;
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
		typeName = getClass().getName();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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

	public Boolean getBajoConsumo() {
		return bajoconsumo;
	}

	public void setBajoConsumo(Boolean bajoconsumo) {
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

	public RestriccionHorasFamilia getRestriccionHoras() {
		return restriccionHoras;
	}

	public void setRestriccionHoras(RestriccionHorasFamilia restriccionHoras) {
		this.restriccionHoras = restriccionHoras;
	}

}
