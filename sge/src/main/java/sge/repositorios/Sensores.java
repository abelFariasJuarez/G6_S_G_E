package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.Sensor;
import sge.modelo.usuarios.Cliente;

public class Sensores extends Repositorio {

	public Sensores(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Sensor findBy(String campo, String valor) {		
		Sensor rhf = (Sensor) this.findBy(Sensor.class,campo,valor);
		return rhf;
	}	

	public List<Sensor> all()
	{
		return (List<Sensor>) this.allOf(Sensor.class);
	}
	
	public void persistir(Sensor c) {
		super.persistir(c);
	}

}
