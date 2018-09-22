package sge.modelo.dispositivo;

import sge.modelo.driver.DriverBasico;

public class DispositivoInteligente extends Inteligente {

	private static Integer puntos = 15;

	/*
	 * @SerializedName ("type") private String type;
	 */
	public DispositivoInteligente(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido,DriverBasico driver) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo, _encendido,driver);

	}

	public DispositivoInteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo,DriverBasico driver) {
		super(_nombre, _consumoPorHora, _bajoconsumo,driver);
	}

	public static Integer puntos() {
		return puntos;
	}
}
