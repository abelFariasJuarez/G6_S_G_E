package sge;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.DispositivoFactoryMethod;
import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoVO;

public class TestRepositorio {
	private Repositorio repositorio;
	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	@Test
	public void cargaBasica() {
		repositorio.cargaDeDatosIniciales();
	}
	
	@Test
	public void metodoAllOf() {
		 List<DispositivoDisponibleVO> disponibles = repositorio.dispositivosDisponibles().all();
		 
		 List<ClienteVO> clienteVOs = repositorio.clientes().all();

		 List<DispositivoVO> dispositivos = repositorio.dispositivos().all();
	}
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
