package sge;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;

public class TestTransformador {
	static Repositorio repositorio = Repositorio.getInstance();
	static Transformadores repoT = repositorio.transformadores();
	static Clientes repoC = repositorio.clientes();

	@BeforeClass
	public static void setUp() {

		repoT.cargarTransformadores();
		repoC.cargarClientesDesdeJson();
	}

	@Test
	public void DistanciaClienteACualquierTransformador() {
		Transformador transfo = repoT.getTransformadores().get(0);

		Cliente cliente = repoC.getClientesJson().get(1);
		assertEquals(0.020173799865175417, transfo.Distancia(cliente), 0);
	}

	@Test
	public void DistanciaClienteATransformadorMasCercano() {
		List<Transformador> transformadores = repoT.getTransformadores();
		Cliente cliente = repoC.getClientesJson().get(1);
		Transformador transfoCercano = Collections.min(transformadores,
				Comparator.comparing(t -> t.Distancia(cliente)));

		assertEquals(0.010039931324470846, transfoCercano.Distancia(cliente), 0);

	}

}
