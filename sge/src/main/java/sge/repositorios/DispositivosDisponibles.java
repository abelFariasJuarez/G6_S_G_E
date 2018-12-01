package sge.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.usuarios.Cliente;

public class DispositivosDisponibles extends Repositorio {

	public DispositivosDisponibles(EntityManager entityManager) {
		super(entityManager);
	}

	public List<DispositivoDisponible> all()
	{
		return (List<DispositivoDisponible>) this.allOf(DispositivoDisponible.class);
	}
	
	public DispositivoDisponible findBy(String campo, String valor) {		
		DispositivoDisponible rhf = (DispositivoDisponible) this.findBy(DispositivoDisponible.class,campo,valor);
		return rhf;
	}
}
