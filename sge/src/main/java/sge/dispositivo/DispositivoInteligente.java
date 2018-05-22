package sge.dispositivo;

import com.google.gson.annotations.SerializedName;

public class DispositivoInteligente extends Inteligente {
	
	private static Integer puntos = 15;

	@SerializedName ("type")
	private String type;
	
	public DispositivoInteligente(String _nombre, Double _consumoPorHora,String _idUserName, boolean _encendido) {
		
		super(_nombre, _consumoPorHora,_idUserName, _encendido);

	}

	
	public static Integer puntos() {
		return puntos;
	}
}
