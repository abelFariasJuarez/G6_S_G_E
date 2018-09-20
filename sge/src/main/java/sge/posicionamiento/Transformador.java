package sge.posicionamiento;

import java.util.ArrayList;
import java.util.List;

import sge.dispositivo.Dispositivo;
import sge.usuarios.Cliente;

public class Transformador {

	private List<Cliente> cli;

	Integer id;
	Integer idZona;
	Ubicacion ubicacion;

	public Transformador(Integer _id, Integer _idZona, Ubicacion _ubicacion) {
		id = _id;
		idZona = _idZona;
		ubicacion = _ubicacion;

	}

	public List<Cliente> getCli() {
		return cli;
	}

	public void setCli(List<Cliente> cli) {
		this.cli = cli;
	}

	public List<Cliente> getClientes() {

		if (cli == null) {

			cli = new ArrayList<Cliente>();
		}
		return (ArrayList<Cliente>) cli;
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
		return Math.sqrt(Math.pow(ubicacion.getLatitud() - cliente.getUbi().getLatitud(), 2)
				+ Math.pow(ubicacion.getLongitud() - cliente.getUbi().getLongitud(), 2));
	}

}