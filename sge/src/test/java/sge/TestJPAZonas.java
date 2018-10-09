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

	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = RepositorioDeZonas.getinstance();
		repositorio.abrir();
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
		repositorio.borrar(zona);
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
