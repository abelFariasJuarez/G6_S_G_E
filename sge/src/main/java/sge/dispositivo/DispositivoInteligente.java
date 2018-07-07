package sge.dispositivo;

import com.google.gson.annotations.SerializedName;

public class DispositivoInteligente extends Inteligente {
	
	

	private static Integer puntos = 15;

	@SerializedName ("type")
	private String type;
	
	public DispositivoInteligente(String _nombre, Double _consumoPorHora,String _idUserName,Boolean _bajoconsumo, Boolean _encendido) {
		
		super(_nombre, _consumoPorHora,_idUserName,_bajoconsumo,_encendido);

	}

	public DispositivoInteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		// TODO Auto-generated constructor stub
	}

	
	public static Integer puntos() {
		return puntos;
	}
}
