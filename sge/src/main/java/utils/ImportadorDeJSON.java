package utils;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.TimerTask;

import sge.Cliente;
import Repositorios.RepositorioDeClientes;

public class ImportadorDeJSON {

	public void run() {
		try {
			lectorDeArchivos lectorDeArchivos = new lectorDeArchivos();
			while(!lectorDeArchivos.lecturaFinalizada()) {
				Gson gson = new Gson();
				Cliente cliente = gson.fromJson(lectorDeArchivos.leerSiguiente(), Cliente.class);
				RepositorioDeClientes.getInstance().guardarCliente(cliente);
			}
			lectorDeArchivos.cerrar();
		} catch(IOException e) {
			e.printStackTrace();
		}
	
}
