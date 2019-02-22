package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.modelo.posicionamiento.ZonaGeografica;

public class ImportadorDeJsonZona extends ImportadorJson {

	public List<ZonaGeografica> getZona() throws Exception {

		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebazonas.json");

		Type tipoListaZona = new TypeToken<List<ZonaGeografica>>() {
		}.getType();
		return (List<ZonaGeografica>) this.myFromJson(tipoListaZona);

	}

}
