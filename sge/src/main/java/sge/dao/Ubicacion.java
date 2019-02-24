package sge.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import sge.dao.IPersistible;

@Entity
@Table(name = "Ubicacion",uniqueConstraints = {@UniqueConstraint(columnNames = {"latitud", "longitud"}) })
public class Ubicacion extends Persistible {
	@Column(name = "latitud")
	private Double latitud;
	@Column(name = "longitud")
	private Double longitud;
	
	public Ubicacion() {;
	}	

	public Ubicacion(Double _lati, Double _long) {
		latitud = _lati;
		longitud = _long;
	}

	public Double getLatitud() {
		return latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}	
}
