package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.modelo.dispositivo.DispositivoDisponible;

public class ImportadorDeJsonDisponible extends ImportadorJson {

	public List<DispositivoDisponible> getDispositivoDisponible(String archivo) throws Exception {
		this.setArchivoFuente(archivo);
		Type typeToken = new TypeToken<List<DispositivoDisponible>>() {
		}.getType();
		List<DispositivoDisponible> DispositivoDisponibles = (List<DispositivoDisponible>) this.myFromJson(typeToken);

		return DispositivoDisponibles;
	}

}
