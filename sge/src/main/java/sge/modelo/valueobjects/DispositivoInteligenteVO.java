package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.DriverBasico;

public class DispositivoInteligenteVO extends InteligenteVO {

	private static Integer puntos = 15;

	public DispositivoInteligenteVO() {
		super();
	}

	public DispositivoInteligenteVO(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido,DriverBasico driver) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo, _encendido,driver);
	}

	public DispositivoInteligenteVO(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo,DriverBasico driver) {
		super(_nombre, _consumoPorHora, _bajoconsumo,driver);
	}

	public static Integer puntos() {
		return puntos;
	}
}
