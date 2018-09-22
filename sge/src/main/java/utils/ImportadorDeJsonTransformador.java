package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.modelo.posicionamiento.Transformador;


public class ImportadorDeJsonTransformador {
	public List<Transformador> getTransformadores() throws IOException {

		String transformadoresJSON = "";

		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebatransformadores.json");
		while (!lectorDeArchivos.lecturaFinalizada()) {
			transformadoresJSON = transformadoresJSON + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Gson gson = new  Gson();/*Builder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();*/
		Type tipoListaTransformador = new TypeToken<List<Transformador>>() {
		}.getType();
		List<Transformador> transformadores = gson.fromJson(transformadoresJSON, tipoListaTransformador );

		return transformadores;
	}

	

}

