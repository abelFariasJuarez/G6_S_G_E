package sge.modelo.posicionamiento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import sge.modelo.IPersistible;
import sge.modelo.Persistible;

@Entity
@Table(name = "Ubicacion",uniqueConstraints = {@UniqueConstraint(columnNames = {"latitud", "longitud"}) })
public class Ubicacion implements Serializable, IPersistible {
	@Id
	@Column(name = "latitud")
	private Double latitud;
	@Id
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

	@Override
	public Long getOid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOid(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
