package sge.repositorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import utils.ImportadorDeJSONCliente;

public class Clientes extends Repositorio {
	private List<Cliente> clientesJson = new ArrayList<Cliente>();

	public Clientes(EntityManager entityManager) {
		super(entityManager);
	};

	public Clientes() {
	}

	public void guardarCliente(Cliente cliente) {
		clientesJson.add(cliente);
	}

	public List<Cliente> getClientesJson() {
		return clientesJson;
	}

	public void cargarClientesDesdeJson() {
		clientesJson.clear();
		ImportadorDeJSONCliente json = new ImportadorDeJSONCliente();
		try {
			this.clientesJson.addAll(json.getClientes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.persistir(this.clientesJson);
	}

	public void persistir(List<Cliente> clientesJson2) {
		clientesJson2.forEach(c -> this.persistir(c));		
	}
	
	public void persistir(Cliente c) {
		Cliente c1 = this.getPersistenteBy("username",c.getUsername());
		Ubicacion ubi = this.ubicaciones().getPersistente(c.getUbicacion().getLongitud(),c.getUbicacion().getLatitud());
		c.setUbicacion(ubi);
		c1.llenarAtributos(c);
		super.persistir(c1);
	}

	private Cliente getPersistenteBy(String campo, Object valor) {
		Cliente transDAO = this.findBy(campo, valor);
		if(transDAO == null)
		{
			transDAO = new Cliente();
		}
		return transDAO;
	}
	
	public List<Cliente> all()
	{
		return (List<Cliente>) this.allDistinctOf(Cliente.class);
	}
	
	public Cliente findBy(String campo, Object valor) {
		Cliente rhf = (Cliente) this.findBy(Cliente.class, campo, valor);
		return rhf;
	}

}
