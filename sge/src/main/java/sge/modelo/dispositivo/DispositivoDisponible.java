package sge.modelo.dispositivo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Table(name = "DispositivoDisponible")
public class DispositivoDisponible extends Persistible {
	
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "isInteligente")
	private Boolean isInteligente;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "isBajoConsumo")
	private Boolean isBajoConsumo;
	@Column(name = "consumoPorHora")
	private Double consumoPorHora;
	@Column(name = "codigoRestriccionHoras")
	private String codigoRestriccionHoras;
	
	public DispositivoDisponible() {
		super();
	}
	
	public DispositivoDisponible(String codigo, Boolean isInteligente, String nombre, Boolean isBajoConsumo,
			Double consumoPorHora, String codigoRestriccionHoras) {
		super();
		this.codigo = codigo;
		this.isInteligente = isInteligente;
		this.nombre = nombre;
		this.isBajoConsumo = isBajoConsumo;
		this.consumoPorHora = consumoPorHora;
		this.codigoRestriccionHoras = codigoRestriccionHoras;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getIsInteligente() {
		return isInteligente;
	}
	public void setIsInteligente(Boolean isInteligente) {
		this.isInteligente = isInteligente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getIsBajoConsumo() {
		return isBajoConsumo;
	}
	public void setIsBajoConsumo(Boolean isBajoConsumo) {
		this.isBajoConsumo = isBajoConsumo;
	}
	public Double getConsumoPorHora() {
		return consumoPorHora;
	}
	public void setConsumoPorHora(Double consumoPorHora) {
		this.consumoPorHora = consumoPorHora;
	}
	public String getCodigoRestriccionHoras() {
		return codigoRestriccionHoras;
	}
	public void setCodigoRestriccionHoras(String codigoRestriccionHoras) {
		this.codigoRestriccionHoras = codigoRestriccionHoras;
	}

}
