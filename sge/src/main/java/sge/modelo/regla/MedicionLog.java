package sge.modelo.regla;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.repositorios.LocalDateTimeConverter;

@Entity
@Table(name = "MedicionLog")
public class MedicionLog extends Persistible{
	@Column(name = "medicion")
	private double medicion;
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime logTime;
	public double getMedicion() {
		return medicion;
	}
	public void setMedicion(double medicion) {
		this.medicion = medicion;
	}
	public LocalDateTime getLogTime() {
		return logTime;
	}
	public void setLogTime(LocalDateTime logTime) {
		this.logTime = logTime;
	}
	
	public MedicionLog(double _medicion) {
		super();
		medicion = _medicion;
		logTime = LocalDateTime.now();
	}

	public MedicionLog() {
		super();
	}
}
