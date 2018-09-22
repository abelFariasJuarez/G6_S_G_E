package sge.modelo.posicionamiento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Table(name = "Ubicacion")
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
