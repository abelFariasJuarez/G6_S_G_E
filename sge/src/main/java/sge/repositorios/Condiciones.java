package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.regla.Condicion;


public class Condiciones extends Repositorio{
	public Condiciones(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Condicion findBy(String campo, Long valor) {		
		Condicion rhf = (Condicion) this.findBy(Condicion.class,campo,valor);
		return rhf;
	}

}