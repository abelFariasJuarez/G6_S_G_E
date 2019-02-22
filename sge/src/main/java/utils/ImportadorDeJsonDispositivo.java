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

public class ImportadorDeJsonDispositivo extends ImportadorJson {

	public List<Dispositivo> agregarDispositivos(String archivo) throws Exception {
		this.setArchivoFuente(archivo);
		Type tipoListaDispositivos = new TypeToken<List<DispositivoInteligente>>() {
		}.getType();

		return (List<Dispositivo>) this.myFromJson(tipoListaDispositivos);
	}

	public List<Dispositivo> getDispositivos(String string) throws Exception {
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

		if (string == "inteligente" || string == "todos") {
			dispositivos.addAll(this.agregarDispositivos(System.getProperty("user.dir") + "/src/test/pruebaDispositivoInteligente.json"));
		}

		if (string == "estandar" || string == "todos") {
			dispositivos.addAll(this.agregarDispositivos(System.getProperty("user.dir") + "/src/test/pruebaDispositivoEstandar.json"));
		}

		return dispositivos;
	}

}
