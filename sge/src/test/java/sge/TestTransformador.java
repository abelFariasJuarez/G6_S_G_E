package sge;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.ZonaVO;

public class TestTransformador {
	static Repositorio repositorio = new Repositorio();
	static Transformadores repoT = repositorio.transformadores();
	static Clientes repoC = repositorio.clientes();

	@BeforeClass
	public static void setUp() {

		repoT.cargarTransformadores();
		repoC.cargarClientesDesdeJson();
	}

	@Test
	public void DistanciaClienteACualquierTransformador() {
		TransformadorVO transfo = repoT.getTransformadores().get(0);

		ClienteVO clienteVO = repoC.getClientesJson().get(1);
		assertEquals(584.3492609553148, transfo.Distancia(clienteVO), 0);
	}

	@Test
	public void DistanciaClienteATransformadorMasCercano() {
		List<TransformadorVO> transformadores = repoT.getTransformadores();
		ClienteVO clienteVO = repoC.getClientesJson().get(1);
		TransformadorVO transfoCercano = Collections.min(transformadores,
				Comparator.comparing(t -> t.Distancia(clienteVO)));

		assertEquals(584.3373307553782, transfoCercano.Distancia(clienteVO), 0);

	}

}
