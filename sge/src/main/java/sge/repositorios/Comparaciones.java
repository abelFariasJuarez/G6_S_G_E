package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;

public class Comparaciones extends Repositorio{
	public Comparaciones(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Comparador findBy(String campo, Long valor) {		
		Comparador rhf = (Comparador) this.findBy(Comparador.class,campo,valor);
		return rhf;
	}

}
