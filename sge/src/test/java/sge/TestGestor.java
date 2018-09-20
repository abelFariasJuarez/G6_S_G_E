package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sge.repositorios.RepositorioDeDispositivos;
import sge.usuarios.Cliente;
import sge.usuarios.GestorCliente;
import sge.dispositivo.Dispositivo;
import sge.dispositivo.Inteligente;
import sge.posicionamiento.Transformador;
import sge.posicionamiento.Ubicacion;
import sge.posicionamiento.ZonaGeografica;

public class TestGestor {


	static GestorCliente gestor = new GestorCliente();
	
	@BeforeClass
	public static void setUp() {
		gestor.transformadoresAsignacionZona();
		gestor.asignarClientesATransformadores();		
	}
	
	@Test
	public void clientePerteneceAZona() {
		ZonaGeografica zona=gestor.getRepoZonas().zonas().get(0);
		Cliente cliente=gestor.getRepoClientes().Clientes().get(0);
		assertEquals(true,zona.pertenece(cliente)); 
		
	}
	
	@Test
	public void esClienteDeAlgunaZona() {
		List<ZonaGeografica> zonas=gestor.getRepoZonas().zonas();
		Cliente cliente=gestor.getRepoClientes().Clientes().get(2);
		assertEquals(true,zonas.stream().anyMatch(zona -> zona.pertenece(cliente))); 
		
	}
	
	@Test
	public void ClientePerteneceATransformador() {
		Transformador transfo=gestor.getRepoTransformadores().transformadores().get(3);
		assertEquals(2,transfo.getClientes().size()); 		
	}
	

}
