package sge;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeTransformadores;
import sge.posicionamiento.Transformador;
import sge.posicionamiento.ZonaGeografica;

public class TestTransformador {
	static RepositorioDeTransformadores repoT=RepositorioDeTransformadores.getinstance();
	static RepositorioDeClientes repoC=RepositorioDeClientes.getinstance();
	@BeforeClass
	public static void setUp() {
	
		
		
	repoT.cargarTransformadores();
	
			repoC.cargarClientes();
	}



	@Test
	public void DistanciaClienteACualquierTransformador() {
		Transformador transfo=repoT.transformadores().get(0);
				
		Cliente cliente=repoC.Clientes().get(1);
		assertEquals(517.2852211304707,transfo.Distancia(cliente),0); 
		
	}
	@Test
	public void DistanciaClienteATransformadorMasCercano() {
		List<Transformador> transformadores=repoT.transformadores();
			Cliente cliente=repoC.Clientes().get(1);
			Transformador transfoCercano = Collections.min(transformadores,
					Comparator.comparing(t -> t.Distancia(cliente)));
		
		assertEquals(4.123105625617661,transfoCercano.Distancia(cliente),0); 
		
	}


	




}

