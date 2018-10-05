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
import sge.repositorios.RepositorioDeTransformadores;

public class TestTransformador {
	static RepositorioDeTransformadores repoT = RepositorioDeTransformadores.getinstance();
	static Clientes repoC = (new Repositorio()).clientes();

	@BeforeClass
	public static void setUp() {

		repoT.cargarTransformadores();

		repoC.cargarClientes();
	}

	@Test
	public void DistanciaClienteACualquierTransformador() {
		Transformador transfo = repoT.transformadores().get(0);

		Cliente cliente = repoC.getClientes().get(1);
		assertEquals(517.2852211304707, transfo.Distancia(cliente), 0);

	}

	@Test
	public void DistanciaClienteATransformadorMasCercano() {
		List<Transformador> transformadores = repoT.transformadores();
		Cliente cliente = repoC.getClientes().get(1);
		Transformador transfoCercano = Collections.min(transformadores,
				Comparator.comparing(t -> t.Distancia(cliente)));

		assertEquals(4.123105625617661, transfoCercano.Distancia(cliente), 0);

	}

}
