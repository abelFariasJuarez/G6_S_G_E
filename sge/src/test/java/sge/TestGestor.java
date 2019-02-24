package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.GestorCliente;
import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.InteligenteVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.UbicacionVO;
import sge.modelo.valueobjects.ZonaVO;

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
		ZonaVO zona=gestor.getRepoZonas().getZonas().get(0);
		ClienteVO clienteVO=gestor.getRepoClientes().getClientesJson().get(0);
		assertEquals(true,zona.pertenece(clienteVO)); 
		
	}
	
	@Test
	public void esClienteDeAlgunaZona() {
		List<ZonaVO> zonas=gestor.getRepoZonas().getZonas();
		ClienteVO clienteVO=gestor.getRepoClientes().getClientesJson().get(2);
		assertEquals(true,zonas.stream().anyMatch(zona -> zona.pertenece(clienteVO))); 
		
	}
	
	@Test
	public void ClientePerteneceATransformador() {
		TransformadorVO transfo=gestor.getRepoTransformadores().getTransformadores().get(3);
		assertEquals(0,transfo.getClientes().size()); 		
	}
	
	@After
	public void tearDown() throws Exception {
		gestor.getRepositorio().cerrar();
	}
}
