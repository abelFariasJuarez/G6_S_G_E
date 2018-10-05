package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.regla.Regla;

public class Reglas extends Repositorio {

	public Reglas(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Regla findBy(String campo, String valor) {		
		Regla rhf = (Regla) this.findBy(Regla.class,campo,valor);
		return rhf;
	}	

}