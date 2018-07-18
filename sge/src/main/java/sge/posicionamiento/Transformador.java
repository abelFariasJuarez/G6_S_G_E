package sge.posicionamiento;

import java.util.ArrayList;
import java.util.List;

import sge.Cliente;
import sge.dispositivo.Dispositivo;

public class Transformador {
	
public  	List<Cliente> cli = new ArrayList<Cliente>();
	Cliente cliente;
	Integer id;
	Integer idZona;
	Ubicacion ubicacion;
	private List<Cliente> clientes;
	
public Transformador(Integer _id,Integer _idZona,Ubicacion _ubicacion) {
	id=_id;
	idZona=_idZona;
	ubicacion=_ubicacion;
	
}
	public Float energiaSuministrada() {
		return (float) cli.stream().mapToDouble(c -> c.ConsumoActualDispositivosInteligentes()).sum();
		
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Integer getIdZona() {
		return idZona;
	}
	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double Distancia(Cliente cliente) {
		return Math.sqrt(Math.pow(ubicacion.getLatitud() - cliente.getUbi().getLatitud(),2)
				+ Math.pow(ubicacion.getLongitud() - cliente.getUbi().getLongitud(),2));
	}

	public void agregar(Cliente cliente) {
	cli.add(cliente);
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return getClientes();
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	
//0.9 3.3 / 1.0 3.0
}