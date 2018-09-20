package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.TimerTask;
import sge.Cliente;
import java.io.IOException;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class ImportadorDeJSONCliente {

	public List<Cliente> getClientes() throws IOException {

		String clientesJSON = "";

		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos("pruebacliente.json");
		while (!lectorDeArchivos.lecturaFinalizada()) {
			clientesJSON = clientesJSON + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Gson gson = new  Gson();/*Builder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();*/
		Type tipoListaEmpleados = new TypeToken<List<Cliente>>() {
		}.getType();
		List<Cliente> clientes = gson.fromJson(clientesJSON, tipoListaEmpleados);

		return clientes;
	}

	
	

}
