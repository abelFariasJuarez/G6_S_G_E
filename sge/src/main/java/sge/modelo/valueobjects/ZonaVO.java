package sge.modelo.valueobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.dao.Persistible;

public class ZonaVO extends Persistible {

	private String nombre;
	private Integer id;
	private UbicacionVO centro;
	private Float radio;	
	private List<TransformadorVO> transformadores = new ArrayList<TransformadorVO>();
	private List<ClienteVO> clientes = new ArrayList<ClienteVO>();

	public List<ClienteVO> getClientes() {
		return clientes;
	}
	
	public Float getRadio() {
		return radio;
	}

	public void setRadio(Float radio) {
		this.radio = radio;
	}

	public void setTransformadores(List<TransformadorVO> transformadores) {
		this.transformadores = transformadores;
	}

	public void setClientes(List<ClienteVO> clientes) {
		this.clientes = clientes;
	}

	public void addCliente(ClienteVO cliente) {
		clientes.add(cliente);
	}

	public void Add(TransformadorVO trans) {
		transformadores.add(trans);
	}

	public List<TransformadorVO> getTransformadores() {
		return transformadores;
	}

	public String getNombre() {
		return nombre;
	}

	public UbicacionVO getCentro() {
		return centro;
	}

	public void setCentro(UbicacionVO centro) {
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

	public void desactivarTransformadores() {
		transformadores = null;
	}

	public Float consumoTotal() {
		return (float) transformadores.stream().mapToDouble(t -> t.energiaSuministrada()).sum();
	}

	public boolean pertenece(ClienteVO clienteVO) {
		return Math.sqrt(Math.pow(centro.getLongitud() - clienteVO.getUbicacion().getLongitud(), 2)
				+ Math.pow((centro.getLatitud() - clienteVO.getUbicacion().getLatitud()), 2)) <= radio;
	}

	public void asignarTransformador(ClienteVO cliente) {

		TransformadorVO trans = Collections.min(transformadores, Comparator.comparing(t -> t.Distancia(cliente)));
		trans.getClientes().add(cliente);

	}

}
