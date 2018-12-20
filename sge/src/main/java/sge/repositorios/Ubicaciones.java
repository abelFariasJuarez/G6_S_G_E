package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;

public class Ubicaciones extends Repositorio{
	public Ubicaciones() {
	};

	public Ubicaciones(EntityManager entityManager) {
		super(entityManager);
	}

	public Ubicacion getPersistente(Double longitud, Double latitud) {
		Ubicacion rhf = (Ubicacion) this.findBy(Ubicacion.class, "longitud","latitud", longitud,latitud);
		if(rhf == null)
		{
			rhf = new Ubicacion();
			rhf.setLatitud(latitud);
			rhf.setLongitud(longitud);
		}
		return rhf;
	}
}
