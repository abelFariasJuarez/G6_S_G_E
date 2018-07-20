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

		Gson gson = new Gson();

		List<Dispositivo> dispositivos = gson.fromJson(dispositivosInteligentes, tipoListaDispositivos);

		while (!lectorDeArchivos2.lecturaFinalizada()) {
			dispositivosEstandar = dispositivosEstandar + lectorDeArchivos2.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Type tipoListaDispositivos2 = new TypeToken<List<DispositivoEstandar>>() {
		}.getType();

		Gson gson2 = new Gson();

		dispositivos.addAll(gson2.fromJson(dispositivosEstandar, tipoListaDispositivos2));

		return dispositivos;
	}

}
