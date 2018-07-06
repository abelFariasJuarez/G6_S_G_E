package posicionamiento;

import java.util.ArrayList;
import java.util.List;

import sge.Cliente;

public class Transformador {

	List<Cliente> clientes = new ArrayList<Cliente>();
	Integer id;
	Integer idZona;
	Ubicacion ubicacion;
	
public Transformador(Integer _id,Integer _idZona,Ubicacion _ubicacion) {
	id=_id;
	idZona=_idZona;
	ubicacion=_ubicacion;
	
}
	public Float energiaSuministrada() {
		return (float) clientes.stream().mapToDouble(c -> c.ConsumoActualDispositivosInteligentes()).sum();

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

	public void add(Cliente cliente) {
		clientes.add(cliente);
		
	}
//0.9 3.3 / 1.0 3.0
}