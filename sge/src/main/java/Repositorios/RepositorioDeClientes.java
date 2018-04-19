package Repositorios;

import java.util.ArrayList;
import java.util.List;
import sge.Cliente;

public class RepositorioDeClientes {
	private static RepositorioDeClientes repo;
	public  List<Cliente> Clientes = new ArrayList<Cliente>();

	private RepositorioDeClientes() {
	};

	public static RepositorioDeClientes getinstance() {
		if (repo == null) {
			repo = new RepositorioDeClientes();
		}
		return repo;
	}

	public void guardarCliente(Cliente cliente) {
		Clientes.add(cliente);

	}

	public  List<Cliente> Clientes() {
		return Clientes;
	}
}
