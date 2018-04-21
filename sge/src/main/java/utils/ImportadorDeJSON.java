package utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.TimerTask;
import sge.Cliente;
import Repositorios.RepositorioDeClientes;
import java.io.IOException;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class ImportadorDeJSON {

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
