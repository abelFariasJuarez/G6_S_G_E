package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.Cliente;
import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoEstandar;
import sge.dispositivo.DispositivoInteligente;

public class ImportadorDeJsonDispositivo {
	
	
	public List<Dispositivo> getDispositivos() throws IOException {
		String dispositivosInteligentes = "";
		String dispositivosEstandar = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebadispositivoInteligente.json");
		lectorDeArchivos lectorDeArchivos2 = new lectorDeArchivos("pruebadispositivoEstandar.json");
		
		while (!lectorDeArchivos.lecturaFinalizada()) {
			dispositivosInteligentes = dispositivosInteligentes + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();


		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligente>>() {
		}.getType();
		
		/*RuntimeTypeAdapterFactory<Dispositivo> dispositivoAdapterFactory = RuntimeTypeAdapterFactory
				.of(Dispositivo.class, "type");

		dispositivoAdapterFactory.registerSubtype(DispositivoInteligente.class ,"inteligente");
		dispositivoAdapterFactory.registerSubtype(DispositivoEstandar.class ,"estandar");
		*/
		//Gson gson = new GsonBuilder().registerTypeAdapterFactory(dispositivoAdapterFactory).create();
		
		Gson gson = new  Gson();
		
		List<Dispositivo> dispositivos = gson.fromJson(dispositivosInteligentes, tipoListaDispositivos);

		while (!lectorDeArchivos2.lecturaFinalizada()) {
			dispositivosEstandar = dispositivosEstandar + lectorDeArchivos2.leerSiguiente();
		}
		lectorDeArchivos.cerrar();


		Type tipoListaDispositivos2 = new TypeToken<List<DispositivoEstandar>>() {
		}.getType();

		Gson gson2 = new  Gson();
		
		dispositivos.addAll( gson2.fromJson(dispositivosEstandar, tipoListaDispositivos2));
		
		
		return dispositivos;
	}



}
