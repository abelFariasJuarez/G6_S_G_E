package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoInteligente;

public class ImportadorDeJsonDispositivo {

	public List<Dispositivo> agregarDispositivos(lectorDeArchivos lector) throws IOException {

		String dispositivosAux = "";

		while (!lector.lecturaFinalizada()) {
			dispositivosAux = dispositivosAux + lector.leerSiguiente();
		}
		lector.cerrar();

		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligente>>() {
		}.getType();

		Gson gson = new Gson();

		return gson.fromJson(dispositivosAux, tipoListaDispositivos);
	}

	public List<Dispositivo> getDispositivos(String string) throws IOException {
		String dispositivosInteligentes = "";
		String dispositivosEstandar = "";
		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebaDispositivoInteligente.json");
		lectorDeArchivos lectorDeArchivos2 = new lectorDeArchivos("pruebaDispositivoEstandar.json");
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

		if (string == "todos") {

			dispositivos.addAll(this.agregarDispositivos(lectorDeArchivos));
			dispositivos.addAll(this.agregarDispositivos(lectorDeArchivos2));
		}

		if (string == "inteligente") {
			dispositivos.addAll(this.agregarDispositivos(lectorDeArchivos));
		}

		if (string == "estandar") {
			dispositivos.addAll(this.agregarDispositivos(lectorDeArchivos2));
		}

		return dispositivos;
	}

}
