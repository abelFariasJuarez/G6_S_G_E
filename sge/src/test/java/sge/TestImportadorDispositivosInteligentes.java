package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.Dispositivo;
import sge.dispositivo.DispositivoInteligente;

public class TestImportadorDispositivosInteligentes {

	static RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	static RepositorioDeDispositivos repo = RepositorioDeDispositivos.getinstance();
	static List<Dispositivo> Dispositivos;

	@Before
	public void setUp() {

		repo.cargarDispositivos("inteligente");
		Dispositivos = repo.Dispositivos();
		for (Dispositivo disp : Dispositivos) {
			disp.presentate();
			// System.out.println(disp.getClass());
		}
	}

	@Test
	public void importacionEstandar() {

		assertEquals(16, Dispositivos.size());

	}

}
