package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import utils.ImportadorDeJSONCliente;

public class Clientes extends Repositorio {
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public Clientes(EntityManager entityManager) {
		super(entityManager);
	};

	public Clientes() {
	}

	public void guardarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void cargarClientes() {
		clientes.clear();
		ImportadorDeJSONCliente json = new ImportadorDeJSONCliente();
		try {
			this.clientes.addAll(json.getClientes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public List<Cliente> all()
	{
		return (List<Cliente>) this.allDistinctOf(Cliente.class);
	}
	
	public Cliente findBy(String campo, String valor) {
		Cliente rhf = (Cliente) this.findBy(Cliente.class, campo, valor);
		return rhf;
	}

}
