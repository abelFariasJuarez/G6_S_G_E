package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;
import sge.repositorios.RepositorioRestriccionHorasFamilia;

public class TestJPARestriccionesHorasFamilia {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = RepositorioRestriccionHorasFamilia.getinstance();
		repositorio.abrir();
	}

	@Test
	public void aPersistir() {		
		assertEquals(true, true);

	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
