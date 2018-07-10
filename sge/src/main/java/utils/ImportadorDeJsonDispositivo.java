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
import sge.dispositivo.familia.AireAcondicionadoInteligente;
import sge.dispositivo.familia.ComputadoraInteligente;
import sge.dispositivo.familia.Heladera;
import sge.dispositivo.familia.LamparaInteligente;
import sge.dispositivo.familia.Lavarropas;
import sge.dispositivo.familia.LavarropasInteligente;
import sge.dispositivo.familia.Microondas;
import sge.dispositivo.familia.Plancha;
import sge.dispositivo.familia.Television;
import sge.dispositivo.familia.TelevisionInteligente;
import sge.dispositivo.familia.Ventilador;
import sge.dispositivo.familia.VentiladorInteligente;

public class ImportadorDeJsonDispositivo {
	
	
	public List<Dispositivo> getDispositivos() throws IOException {
		String dispositivosInteligentes = "";
		String dispositivosEstandar = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebaDispositivoInteligente.json");
		lectorDeArchivos lectorDeArchivos2 = new lectorDeArchivos("pruebaDispositivoEstandar.json");
		
		while (!lectorDeArchivos.lecturaFinalizada()) {
			dispositivosInteligentes = dispositivosInteligentes + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();
		
		
		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligente>>() {
		}.getType();
		
		RuntimeTypeAdapterFactory<Dispositivo> dispositivoAdapterFactory = RuntimeTypeAdapterFactory
				.of(Dispositivo.class, "type");

		dispositivoAdapterFactory.registerSubtype(Heladera.class ,"heladera");
		dispositivoAdapterFactory.registerSubtype(AireAcondicionadoInteligente.class ,"aireAcondicionadoInt");
		dispositivoAdapterFactory.registerSubtype(ComputadoraInteligente.class ,"computadoraInt");
		dispositivoAdapterFactory.registerSubtype(LamparaInteligente.class ,"lamparaInt");
		dispositivoAdapterFactory.registerSubtype(LavarropasInteligente.class ,"lavarropasInt");
		dispositivoAdapterFactory.registerSubtype(TelevisionInteligente.class ,"tvInt");
		dispositivoAdapterFactory.registerSubtype(VentiladorInteligente.class ,"ventiladorInt");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(dispositivoAdapterFactory).create();
		
		//Gson gson = new  Gson();
		
		List<Dispositivo> dispositivos = gson.fromJson(dispositivosInteligentes, tipoListaDispositivos);
		
		
		while (!lectorDeArchivos2.lecturaFinalizada()) {
			dispositivosEstandar = dispositivosEstandar + lectorDeArchivos2.leerSiguiente();
		}
		lectorDeArchivos.cerrar();


		Type tipoListaDispositivos2 = new TypeToken<List<DispositivoEstandar>>() {
		}.getType();

		RuntimeTypeAdapterFactory<Dispositivo> dispositivoAdapterFactory2 = RuntimeTypeAdapterFactory
				.of(Dispositivo.class, "type");

		dispositivoAdapterFactory2.registerSubtype(Lavarropas.class ,"lavarropas");
		dispositivoAdapterFactory2.registerSubtype(Microondas.class ,"microondas");
		dispositivoAdapterFactory2.registerSubtype(Plancha.class ,"plancha");
		dispositivoAdapterFactory2.registerSubtype(Television.class ,"television");
		dispositivoAdapterFactory2.registerSubtype(Ventilador.class ,"ventilador");

		
		Gson gson2 = new GsonBuilder().registerTypeAdapterFactory(dispositivoAdapterFactory2).create();
		
		//Gson gson = new  Gson();
		
		//List<Dispositivo> dispositivos = gson.fromJson(dispositivosInteligentes, tipoListaDispositivos);

		
		
		
		
		
		//Gson gson2 = new  Gson();
		
		dispositivos.addAll( gson2.fromJson(dispositivosEstandar, tipoListaDispositivos2));
		
		
		return dispositivos;
	}



}
