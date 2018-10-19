package sge.modelo.posicionamiento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.modelo.usuarios.Cliente;

@Entity
@Table(name = "Transformador")
@NamedQuery(name = "buscarTodosTransformadores", query = "SELECT t FROM Transformador t")
public class Transformador extends Persistible {

	@OneToMany
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "idZona")	
	private Integer idZona;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Ubicacion ubicacion;

	public Transformador() {
	}

	public Transformador(Long _id, Integer _idZona, Ubicacion _ubicacion) {
		id = _id;
		idZona = _idZona;
		ubicacion = _ubicacion;

	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> cli) {
		this.clientes = cli;
	}

	public Float energiaSuministrada() {
		return (float) clientes.stream().mapToDouble(c -> c.ConsumoActualDispositivosInteligentes()).sum();
	}

	public Double consumoEnPeriodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		double valueReturn = clientes.stream().mapToDouble(c -> c.consumoEnPeriodo(instanteDesde,instanteHasta)).sum();

		return valueReturn;
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

	public Double Distancia(Cliente cliente) {
		return Math.sqrt(Math.pow(ubicacion.getLatitud() - cliente.getUbicacion().getLatitud(), 2)
				+ Math.pow(ubicacion.getLongitud() - cliente.getUbicacion().getLongitud(), 2));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addCliente(Cliente unCliente) {
		clientes.add(unCliente);		
	}

	public void removeCliente(Cliente unCliente) {
		clientes.remove(unCliente);		
	}

}