package sge.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.UbicacionVO;

@Entity
@Table(name = "Transformador")
@NamedQuery(name = "buscarTodosTransformadores", query = "SELECT t FROM Transformador t")
public class Transformador extends Persistible {

	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@Column(name = "id", unique=true)
	private Long id;
	
	@Column(name = "idZona")	
	private Integer idZona;
	
	@ManyToOne(cascade = CascadeType.ALL)
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

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacionVO) {
		this.ubicacion = ubicacionVO;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
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
