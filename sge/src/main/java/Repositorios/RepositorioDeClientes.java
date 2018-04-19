package Repositorios;

import java.util.ArrayList;
import java.util.List;
import sge.Cliente;

public class RepositorioDeClientes {
	private static RepositorioDeClientes repoClientes;
	public  List<Cliente> Clientes = new ArrayList<Cliente>();

	private RepositorioDeClientes() {
	};

	public static RepositorioDeClientes getinstance() {
		if (repoClientes == null) {
			repoClientes = new RepositorioDeClientes();
		}
		return repoClientes;
	}

	public void guardarCliente(Cliente cliente) {
		Clientes.add(cliente);

	}

	public  List<Cliente> Clientes() {
		return Clientes;
	}
}
