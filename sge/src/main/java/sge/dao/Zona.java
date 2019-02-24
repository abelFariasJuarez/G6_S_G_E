package sge.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.UbicacionVO;
import sge.modelo.valueobjects.ZonaVO;

@Entity
@Table(name = "Zona")
public class Zona extends Persistible {
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "id", unique=true)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Ubicacion centro;
	
	@Column(name = "radio")
	private Float radio;	
	
	@OneToMany(cascade = CascadeType.ALL)	
	private List<Transformador> transformadores = new ArrayList<Transformador>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public Float getRadio() {
		return radio;
	}

	public void setRadio(Float radio) {
		this.radio = radio;
	}

	public void setTransformadores(List<Transformador> transformadores) {
		this.transformadores = transformadores;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void Add(Transformador trans) {
		transformadores.add(trans);
	}

	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public String getNombre() {
		return nombre;
	}

	public Ubicacion getCentro() {
		return centro;
	}

	public void setCentro(Ubicacion centro) {
		this.centro = centro;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
