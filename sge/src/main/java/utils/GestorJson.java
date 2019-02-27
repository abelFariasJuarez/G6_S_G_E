package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import sge.modelo.dispositivo.Dispositivo;

public abstract class GestorJson {
	private String archivoFuente;
	static GsonBuilder builder;

	public String getArchivoFuente() {
		return archivoFuente;
	}

	public void setArchivoFuente(String archivo) {
		this.archivoFuente = archivo;
	}

	public String getStringForJson() throws Exception {
		String txt = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos(this.getArchivoFuente());
		while (!lectorDeArchivos.lecturaFinalizada()) {
			txt = txt + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();
		return txt;
	}
	
	public List<?> myFromJson(Type typeToken) throws Exception {
		String stringJson = this.getStringForJson();
		Gson gson = new Gson();
		return gson.fromJson(stringJson, typeToken);
	}

	public static String toJson(Object xxx) {
		Gson gson = getGson();
		String json = gson.toJson(xxx);
		return json;
	}	
	
	private static Gson getGson()
	{
		builder = new GsonBuilder(); 
		builder.setPrettyPrinting();
		 Gson gson = builder.create();
		 return gson;
	}
}
