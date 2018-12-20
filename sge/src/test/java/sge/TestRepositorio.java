package sge;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.dispositivo.DispositivoFactoryMethod;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;

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
		 List<DispositivoDisponible> disponibles = repositorio.dispositivosDisponibles().all();
		 
		 List<Cliente> clientes = repositorio.clientes().all();

		 List<Dispositivo> dispositivos = repositorio.dispositivos().all();
	}
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
