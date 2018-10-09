package sge;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;
import sge.repositorios.RepositorioDeZonas;

public class TestJPATransformadores {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();		
	}

	@Test
	public void aPersistir() {
		
		Transformador c = new Transformador();
		c.setIdZona(4);
		c.setUbicacion(new Ubicacion(1.0,1.0));
		repositorio.persistir(c);
		repositorio.borrar(c);
		
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
