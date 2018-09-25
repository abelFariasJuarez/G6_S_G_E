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
import sge.repositorios.RepositorioDeTransformadores;
import sge.repositorios.RepositorioDeZonas;

public class TestJPATransformadores {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = RepositorioDeTransformadores.getinstance();
		repositorio.abrir();		
	}

	@Test
	public void aPersistir() {
		Transformador c = new Transformador();
		c.setIdZona(4);
		c.setUbicacion(new Ubicacion(1.0,1.0));
		repositorio.persistir(c);
	}

	/*@Test
	public void buscarComunaPorId() {
		Comuna comuna = repositorio.comunas().buscarPorId(1L);
		System.out.println("Comuna encontrada por ID: " + comuna.getNombre());
	}*/

	/*@Test
	public void buscarPoiPorId() {
		Poi poi = repositorio.pois().buscarPorId(2L);
		System.out.println("Poi encontrado por ID: " + poi.getNombre());
	}

	@Test
	public void buscarPoiPorNombre() {
		List<Poi> pois = repositorio.pois().buscarPoiPorNombre("JUM");
		for (Poi poi : pois) {
			System.out.println("Encontró el punto de interés: " + poi.getNombre());
		}
	}*/

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
