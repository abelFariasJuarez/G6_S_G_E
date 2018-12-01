package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.regla.Sensor;

public class Sensores extends Repositorio {

	public Sensores(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Sensor findBy(String campo, String valor) {		
		Sensor rhf = (Sensor) this.findBy(Sensor.class,campo,valor);
		return rhf;
	}	

	public List<Sensores> all()
	{
		return (List<Sensores>) this.allOf(Sensores.class);
	}

}
