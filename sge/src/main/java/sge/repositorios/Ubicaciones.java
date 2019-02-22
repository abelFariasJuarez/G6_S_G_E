package sge.repositorios;

import javax.persistence.EntityManager;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;

public class Ubicaciones extends Repositorio{
	public Ubicaciones() {
	};

	public Ubicaciones(EntityManager entityManager) {
		super(entityManager);
	}

	public Ubicacion getPersistente(Ubicacion u) {
		Ubicacion ubi = getPersistente(u.getLongitud(), u.getLatitud());
		return ubi;
	}
	
	public Ubicacion getPersistente(Double longitud, Double latitud) {
		Ubicacion rhf = (Ubicacion) this.findBy(Ubicacion.class, "longitud","latitud", longitud,latitud);
		if(rhf == null)
		{
			rhf = new Ubicacion();
			rhf.setLatitud(latitud);
			rhf.setLongitud(longitud);
			super.persistir(rhf);
		}
		return rhf;
	}
	
	public Ubicacion findByOid(Object valor) {
		Ubicacion rhf = (Ubicacion) this.findBy(Ubicacion.class,"oid", valor);
		return rhf;
	}	
}
