package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;

public class TestImportadorDispositivosEstandar {

	Dispositivos repoDispositivos = new Repositorio().dispositivos();
	List<Dispositivo> dispositivos;

	@Before
	public void setUp() {

		repoDispositivos.cargarDispositivos("estandar");
		dispositivos = repoDispositivos.getDispositivos();
		// for (Dispositivo disp : Dispositivos) {
		// disp.presentate();
		// System.out.println(disp.getClass());
		// }
	}

	@Test
	public void importacionEstandar() {

		assertEquals(8, dispositivos.size());

	}

}
