package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.posicionamiento.ZonaGeografica;


public class ImportadorDeJsonZona {
	

		public List<ZonaGeografica> getZona() throws IOException {

			String zonasJSON = "";

			lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebazonas.json");
			while (!lectorDeArchivos.lecturaFinalizada()) {
				zonasJSON = zonasJSON + lectorDeArchivos.leerSiguiente();
			}
			lectorDeArchivos.cerrar();

			Gson gson = new  Gson();/*Builder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();*/
			Type tipoListaZona = new TypeToken<List<ZonaGeografica>>() {
			}.getType();
			List<ZonaGeografica> zonas = gson.fromJson(zonasJSON, tipoListaZona );

			return zonas;
		}

		

	}



