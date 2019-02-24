package sge.modelo.valueobjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.dao.Persistible;

public class TransformadorVO extends Persistible {

	private List<ClienteVO> clientes = new ArrayList<ClienteVO>();
	private Long id;
	private Integer idZona;
	private UbicacionVO ubicacion;
	
	public TransformadorVO() {
	}

	public TransformadorVO(Long _id, Integer _idZona, UbicacionVO _ubicacion) {
		id = _id;
		idZona = _idZona;
		ubicacion = _ubicacion;
	}

	public List<ClienteVO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteVO> cli) {
		this.clientes = cli;
	}

	public Float energiaSuministrada() {
		return (float) clientes.stream().mapToDouble(c -> c.ConsumoActualDispositivosInteligentes()).sum();
	}

	public Double consumoEnPeriodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		double valueReturn = clientes.stream().mapToDouble(c -> c.consumoEnPeriodo(instanteDesde,instanteHasta)).sum();

		return valueReturn;
	}	

	public UbicacionVO getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionVO ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getIdZona() {
		return idZona;
	}

	public void setIdZona(Integer idZona) {
		this.idZona = idZona;
	}

	public Double Distancia(ClienteVO clienteVO) {
		return Math.sqrt(Math.pow(ubicacion.getLatitud() - clienteVO.getUbicacion().getLatitud(), 2)
				+ Math.pow(ubicacion.getLongitud() - clienteVO.getUbicacion().getLongitud(), 2));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void addCliente(ClienteVO unCliente) {
		clientes.add(unCliente);		
	}

	public void removeCliente(ClienteVO unCliente) {
		clientes.remove(unCliente);		
	}

	public void llenarAtributos(TransformadorVO t) {
		setClientes(t.getClientes());
		setId(t.getId());		
		setIdZona(t.getIdZona());		
		setUbicacion(t.getUbicacion());
		
	}

}