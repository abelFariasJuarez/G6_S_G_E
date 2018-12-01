package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.regla.Condicion;


public class Condiciones extends Repositorio{
	public Condiciones(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Condicion findBy(String campo, Long valor) {		
		Condicion rhf = (Condicion) this.findBy(Condicion.class,campo,valor);
		return rhf;
	}

	public List<Condicion> all()
	{
		return (List<Condicion>) this.allOf(Condicion.class);
	}

}