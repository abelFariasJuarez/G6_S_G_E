package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.Inteligente;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.GestorCliente;

public class TestGestor {


	static GestorCliente gestor = new GestorCliente();
	
	@BeforeClass
	public static void setUp() {
		gestor.getRepositorio().abrir();
		gestor.cargarClientesZonasTransformadores();
		gestor.transformadoresAsignacionZona();
		gestor.asignarClientesATransformadores();
		gestor.getRepositorio().cerrar();
	}

	@Before
	public void setUpBefore() throws Exception {
		gestor.getRepositorio().abrir();
	}
	
	@Test
	public void clientePerteneceAZona() {
		ZonaGeografica zona=gestor.getRepoZonas().getZonas().get(0);
		Cliente cliente=gestor.getRepoClientes().getClientesJson().get(0);
		assertEquals(true,zona.pertenece(cliente)); 
		
	}
	
	@Test
	public void esClienteDeAlgunaZona() {
		List<ZonaGeografica> zonas=gestor.getRepoZonas().getZonas();
		Cliente cliente=gestor.getRepoClientes().getClientesJson().get(2);
		assertEquals(true,zonas.stream().anyMatch(zona -> zona.pertenece(cliente))); 
		
	}
	
	@Test
	public void ClientePerteneceATransformador() {
		Transformador transfo=gestor.getRepoTransformadores().getTransformadores().get(3);
		assertEquals(0,transfo.getClientes().size()); 		
	}
	
	@After
	public void tearDown() throws Exception {
		gestor.getRepositorio().cerrar();
	}
}
