package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.TimerTask;
import sge.Cliente;
import Repositorios.RepositorioDeClientes;
import java.io.IOException;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class ImportadorDeJSON {

	public List<Cliente> getClientes() throws IOException {

		String clientesJSON = "";

		lectorDeArchivos lectorDeArchivos = new lectorDeArchivos();
		while (!lectorDeArchivos.lecturaFinalizada()) {
			clientesJSON = clientesJSON + lectorDeArchivos.leerSiguiente();
		}
		lectorDeArchivos.cerrar();

		Gson gson = new Gson();
		Type tipoListaEmpleados = new TypeToken<List<Cliente>>() {
		}.getType();
		List<Cliente> clientes = gson.fromJson(clientesJSON, tipoListaEmpleados);

		return clientes;
	}

	public void run() {
		try {
			lectorDeArchivos lectorDeArchivos = new lectorDeArchivos();
			while (!lectorDeArchivos.lecturaFinalizada()) {
				Gson gson = new Gson();
				Cliente cliente = gson.fromJson(lectorDeArchivos.leerSiguiente(), Cliente.class);
				RepositorioDeClientes.getinstance().guardarCliente(cliente);

			}
			lectorDeArchivos.cerrar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
