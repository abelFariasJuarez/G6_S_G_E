package posicionamiento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.Cliente;

public class ZonaGeografica {
String nombre;
Integer id;
List<Transformador> transformadores = new ArrayList<Transformador>();
List<Cliente> clientes=new ArrayList<Cliente>();
Ubicacion centro;
Float radio;

public void Add(Transformador trans) {
	transformadores.add(trans);
}
public List<Transformador> getTransformadores() {
return transformadores;
}
public String getNombre() {
	return nombre;
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

public Float consumoTotal() {
return 	(float) transformadores.stream().mapToDouble(t->t.energiaSuministrada()).sum();
}
public boolean pertenece(Cliente cliente) {
return	Math.sqrt (Math.pow(centro.getLongitud()-cliente.getUbi().getLongitud(),2) + Math.pow((centro.getLatitud()-cliente.getUbi().getLatitud()), 2)) <= radio;
}
public void asignarTransformador(Cliente cliente) {
	
		Transformador trans = Collections.min(transformadores, Comparator.comparing(t->t.Distancia(cliente)));
		trans.add(cliente);
	
}
}
