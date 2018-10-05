package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.usuarios.Administrador;
import sge.modelo.usuarios.Categoria;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;

public class TestEntrega3 {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	@Test
	public void casoDePrueba1() {

		// Caso de prueba 1: Crear 1 usuario nuevo.
		Cliente clienteOriginal = repositorio.clientes().findBy("username", "fperez");
		if (clienteOriginal == null) {
			clienteOriginal = new Cliente("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez",
					"menToL2017", "Dni", 11222333, 1543312311);
			clienteOriginal.setAhorroAutomatico(false);
			clienteOriginal.setUbicacion(new Ubicacion(10.0, 90.0));
		}
		// Persistirlo.
		repositorio.persistir(clienteOriginal);

		// Recuperarlo,
		Cliente clienteRecuperado = repositorio.clientes().findBy("username", "fperez");

		// modificar la geolocalización
		clienteRecuperado.getUbicacion().setLongitud(130.0);

		// y grabarlo.
		repositorio.persistir(clienteRecuperado);

		// Recuperarlo
		Cliente clienteRecuperado2 = repositorio.clientes().findBy("username", "fperez");

		// y evaluar que el cambio se haya realizado.
		assertEquals(clienteRecuperado2.getUbicacion().getLongitud(), 130.0, 0);
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}
}
