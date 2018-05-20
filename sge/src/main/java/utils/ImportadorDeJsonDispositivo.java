package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import sge.dispositivo.*;

public class ImportadorDeJsonDispositivo {

	public List<Dispositivo> getDispositivos() throws IOException {
		String dispositivosJSON = "";

		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebadispositivo.json");
		while (!lectorDeArchivos.lecturaFinalizada()) {
			dispositivosJSON = dispositivosJSON + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Gson gson = new Gson();
		Type tipoListaDispositivos = new TypeToken<List<Dispositivo>>() {
		}.getType();
		
		List<Dispositivo> dispositivos = gson.fromJson(dispositivosJSON, tipoListaDispositivos);

		return dispositivos;
	}



}
