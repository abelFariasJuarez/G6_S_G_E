package Repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sge.Cliente;
import utils.ImportadorDeJSONCliente;

public class RepositorioDeClientes {
	private static RepositorioDeClientes repoClientes;
	public List<Cliente> clientes = new ArrayList<Cliente>();

	private RepositorioDeClientes() {
	};

	public static RepositorioDeClientes getinstance() {
		if (repoClientes == null) {
			repoClientes = new RepositorioDeClientes();
		}
		return repoClientes;
	}

	public void guardarCliente(Cliente cliente) {
		clientes.add(cliente);

	}

	public List<Cliente> Clientes() {
		return clientes;
	}

	public void cargarClientes() {
		ImportadorDeJSONCliente json = new ImportadorDeJSONCliente();

		try {
			this.clientes.addAll(json.getClientes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
