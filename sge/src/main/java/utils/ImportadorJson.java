package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;

public abstract class ImportadorJson {
	private String archivoFuente;

	public String getArchivoFuente() {
		return archivoFuente;
	}

	public void setArchivoFuente(String archivo) {
		this.archivoFuente = archivo;
	}

	public String getStringJson() throws Exception {
		String txt = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos(this.getArchivoFuente());
		while (!lectorDeArchivos.lecturaFinalizada()) {
			txt = txt + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();
		return txt;
	}
	
	public List<?> myFromJson(Type typeToken) throws Exception {
		String stringJson = this.getStringJson();
		Gson gson = new Gson();
		return gson.fromJson(stringJson, typeToken);
	}

}
