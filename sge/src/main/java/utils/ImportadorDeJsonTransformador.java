package utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import sge.modelo.posicionamiento.Transformador;


public class ImportadorDeJsonTransformador extends ImportadorJson {
	
	public List<Transformador> getTransformadores() throws Exception {
		
		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebatransformadores.json");
		Type tipoListaTransformador = new TypeToken<List<Transformador>>() {
		}.getType();
		
		return (List<Transformador>) this.myFromJson(tipoListaTransformador);
	}

	

}

