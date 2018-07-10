package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.Dispositivo;
import sge.posicionamiento.Transformador;
import sge.posicionamiento.Ubicacion;

public class TestZonasyTransformadores {

	List<Transformador> transformadores=new ArrayList<Transformador>();
	GestorCliente gestor = new GestorCliente();
	Cliente unCliente=new Cliente("Carlos", "Sanazki", "condarco 148",LocalDate.of(2017,4,7), "cazana", "menToL2017", "Dni", 21321012,1543312310,new Ubicacion(0.21,2.9));
	@Before
	public void setUp() {
	
		gestor.repoClientes.clientes().add(unCliente);
		gestor.transformadoresAsignacionZona();
		gestor.asignarClientesATransformadores();
		
		
	}
	@Test
	public void clientePerteneceAZona() {
		
		assertEquals(true,gestor.repoZonas.zonas().get(0).pertenece(unCliente)); 
		
	}
	
}
