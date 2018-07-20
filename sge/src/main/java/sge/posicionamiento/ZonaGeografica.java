package sge.posicionamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.Cliente;

public class ZonaGeografica {
private String nombre;
private Integer id;
private List<Transformador> transformadores = new ArrayList<Transformador>();
private List<Cliente> clientes=new ArrayList<Cliente>();
private Ubicacion centro;
private Float radio;

public List<Cliente> getClientes() {
	return clientes;
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
return 	(float) transformadores.stream().mapToDouble(t->t.energiaSuministrada()).sum();
}
public boolean pertenece(Cliente cliente) {
return	Math.sqrt (Math.pow(centro.getLongitud()-cliente.getUbi().getLongitud(),2) + Math.pow((centro.getLatitud()-cliente.getUbi().getLatitud()), 2)) <= radio;
}


public void asignarTransformador(Cliente cliente) {
	
	
		Transformador trans = Collections.min(transformadores, Comparator.comparing(t->t.Distancia(cliente)));
		trans.getClientes().add(cliente);
	
}
}
