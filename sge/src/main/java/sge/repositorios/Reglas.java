package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.Regla;
import sge.modelo.usuarios.Cliente;

public class Reglas extends Repositorio {

	public Reglas(EntityManager entityManager) {
		super(entityManager);
	}
	
	public Regla findBy(String campo, String valor) {		
		Regla rhf = (Regla) this.findBy(Regla.class,campo,valor);
		return rhf;
	}	

	public List<Regla> all()
	{
		return (List<Regla>) this.allOf(Regla.class);
	}
	
	public void persistir(Regla c) {
		super.persistir(c);
	}
}