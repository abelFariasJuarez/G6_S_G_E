package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;

import sge.modelo.usuarios.Cliente;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.TimerTask;
import java.io.IOException;
import java.util.TimerTask;

@SuppressWarnings("unused")
public class ImportadorDeJSONCliente extends ImportadorJson {

	public List<Cliente> getClientes() throws Exception {
		this.setArchivoFuente(System.getProperty("user.dir") + "/src/test/pruebacliente.json");
		Type typeToken = new TypeToken<List<Cliente>>() {
		}.getType();
		List<Cliente> clientes = (List<Cliente>) myFromJson(typeToken);
		return clientes;
	}

}
