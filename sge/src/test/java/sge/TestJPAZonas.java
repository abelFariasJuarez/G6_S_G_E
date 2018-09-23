package sge;

import java.time.LocalDate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;
import sge.repositorios.RepositorioDeZonas;

public class TestJPAZonas {

	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = RepositorioDeZonas.getinstance();
		repositorio.setEntityManager(emFactory.createEntityManager());
	}

	@Test
	public void aPersistir() {
		Transformador tran = new Transformador();
		tran.setIdZona(6);
		tran.setUbicacion(new Ubicacion(1.2, 2.0));

		Cliente c = new Cliente("Carl", "adfg", "condarco 125", LocalDate.of(2017, 4, 7), "faras", "mencoco125", "Dni",
				21321013, 1543312311);
		c.setAhorroAutomatico(false);
		c.setUbicacion(new Ubicacion(1.3, 2.1));

		ZonaGeografica zona = new ZonaGeografica();
		zona.setCentro(new Ubicacion(1.2, 2.0));
		zona.addCliente(c);
		zona.setId(125);
		zona.setNombre("zona1");
		zona.setRadio((float) 12.3);
		zona.Add(tran);

		repositorio.persistir(zona);
	}

	/*
	 * @Test public void buscarComunaPorId() { Comuna comuna =
	 * repositorio.comunas().buscarPorId(1L);
	 * System.out.println("Comuna encontrada por ID: " + comuna.getNombre()); }
	 */

	/*
	 * @Test public void buscarPoiPorId() { Poi poi =
	 * repositorio.pois().buscarPorId(2L);
	 * System.out.println("Poi encontrado por ID: " + poi.getNombre()); }
	 * 
	 * @Test public void buscarPoiPorNombre() { List<Poi> pois =
	 * repositorio.pois().buscarPoiPorNombre("JUM"); for (Poi poi : pois) {
	 * System.out.println("Encontró el punto de interés: " + poi.getNombre()); } }
	 */

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
		emFactory.close();
	}

}
