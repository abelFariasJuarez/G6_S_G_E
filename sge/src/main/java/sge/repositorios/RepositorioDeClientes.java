package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import utils.ImportadorDeJSONCliente;

public class RepositorioDeClientes extends Repositorio{
	private static RepositorioDeClientes repoClientes;
	private List<Cliente> clientes = new ArrayList<Cliente>();

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
		clientes.clear();
		ImportadorDeJSONCliente json = new ImportadorDeJSONCliente();

		try {
			this.clientes.addAll(json.getClientes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Cliente> clientes() {
		return clientes;
	}
}
