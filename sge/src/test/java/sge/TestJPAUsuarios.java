package sge;

import java.time.LocalDate;
import java.time.Month;

import javax.persistence.Column;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.usuarios.Administrador;
import sge.modelo.usuarios.Categoria;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;

public class TestJPAUsuarios {

	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	@Test
	public void aPersistirCliente() {
		Cliente c = new Cliente("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321013, 1543312311);
		c.setAhorroAutomatico(false);
		c.setUbicacion(new Ubicacion(1.0,1.0));

		Categoria unaCate = new Categoria("R0", 13.f, 0.05f, 1.5f, 10f);
		c.setCategoria(unaCate);
		repositorio.persistir(c);
	}

	@Test
	public void aPersistirAdministrador() {
		
		Administrador admin = new Administrador("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
				"pepe", "pasti");
		repositorio.persistir(admin);
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
