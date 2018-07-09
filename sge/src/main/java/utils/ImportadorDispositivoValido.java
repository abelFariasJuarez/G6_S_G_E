package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoEstandar;
import sge.dispositivo.DispositivoInteligente;

public class ImportadorDispositivoValido {
	
	
	public List<Dispositivo> getDispositivos() throws IOException {
		String dispositivosJSON = "";

		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebaCargaDispositivo.json");
		while (!lectorDeArchivos.lecturaFinalizada()) {
			dispositivosJSON = dispositivosJSON + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();


		Type tipoListaDispositivos = new TypeToken<List<Dispositivo>>() {
		}.getType();
		
		/*RuntimeTypeAdapterFactory<Dispositivo> dispositivoAdapterFactory = RuntimeTypeAdapterFactory
				.of(Dispositivo.class, "type");

		dispositivoAdapterFactory.registerSubtype(DispositivoInteligente.class ,"inteligente");
		dispositivoAdapterFactory.registerSubtype(DispositivoEstandar.class ,"estandar");
		*/
		Gson gson = new  Gson();
	
		
		//Gson gson = new GsonBuilder().registerTypeAdapterFactory(dispositivoAdapterFactory).create();
		List<Dispositivo> dispositivos = gson.fromJson(dispositivosJSON, tipoListaDispositivos);

		return dispositivos;
	}



}
