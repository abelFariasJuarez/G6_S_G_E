package sge.modelo.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import sge.dao.IPersistible;

public class UbicacionVO implements Serializable {
	Long oid;
	private Double latitud;
	private Double longitud;	
	public UbicacionVO() {;
	}	

	public UbicacionVO(Double _lati, Double _long) {
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

	public Long getOid() {
		return this.oid;
	}

	public void setOid(Long id) {
		this.oid = id;
	}	
	
}
