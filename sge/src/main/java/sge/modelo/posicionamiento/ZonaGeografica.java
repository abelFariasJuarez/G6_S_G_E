package sge.modelo.posicionamiento;

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

import sge.modelo.Persistible;
import sge.modelo.usuarios.Cliente;

@Entity
@Table(name = "ZonaGeografica")
public class ZonaGeografica extends Persistible {

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "id", unique=true)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
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

	public void desactivarTransformadores() {
		transformadores = null;
	}

	public Float consumoTotal() {
		return (float) transformadores.stream().mapToDouble(t -> t.energiaSuministrada()).sum();
	}

	public boolean pertenece(Cliente cliente) {
		return Math.sqrt(Math.pow(centro.getLongitud() - cliente.getUbicacion().getLongitud(), 2)
				+ Math.pow((centro.getLatitud() - cliente.getUbicacion().getLatitud()), 2)) <= radio;
	}

	public void asignarTransformador(Cliente cliente) {

		Transformador trans = Collections.min(transformadores, Comparator.comparing(t -> t.Distancia(cliente)));
		trans.getClientes().add(cliente);

	}

	public void llenarAtributos(ZonaGeografica zona) {
		this.setId(zona.getId());
		this.setNombre(zona.getNombre());
		this.setCentro(zona.getCentro());
		this.setRadio(zona.getRadio());	
		this.setTransformadores(zona.getTransformadores());
		this.setClientes(zona.getClientes());		
	}
}
