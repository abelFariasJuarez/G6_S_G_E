package sge.modelo.valueobjects;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.dao.Persistible;

public class MedicionLogVO {

	private double medicion;
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
	
	public MedicionLogVO(double _medicion) {
		super();
		medicion = _medicion;
		logTime = LocalDateTime.now();
	}

	public MedicionLogVO() {
		super();
	}
}
