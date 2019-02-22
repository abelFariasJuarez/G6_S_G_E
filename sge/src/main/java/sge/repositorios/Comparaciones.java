package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;
import sge.modelo.usuarios.Cliente;

public class Comparaciones extends Repositorio{
	public Comparaciones(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Comparador findBy(String campo, Long valor) {		
		Comparador rhf = (Comparador) this.findBy(Comparador.class,campo,valor);
		return rhf;
	}

	public List<Comparador> all()
	{
		return (List<Comparador>) this.allOf(Comparador.class);
	}

	public void persistir(Comparador c) {
		super.persistir(c);
	}
}
