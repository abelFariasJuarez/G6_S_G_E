package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sge.repositorios.RepositorioDeDispositivos;
import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoInteligente;

public class TestImportadorDispositivosEstandar {

	RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	RepositorioDeDispositivos repo = RepositorioDeDispositivos.getinstance();
	List<Dispositivo> Dispositivos;
	List<Dispositivo> Dispositivos2;

	@Before
	public void setUp() {

		repo.cargarDispositivos("estandar");
		Dispositivos = repo.Dispositivos();
		// for (Dispositivo disp : Dispositivos) {
		// disp.presentate();
		// System.out.println(disp.getClass());
		// }
	}

	@Test
	public void importacionEstandar() {

		assertEquals(8, Dispositivos.size());

	}

}
