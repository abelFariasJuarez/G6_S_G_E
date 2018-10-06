package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;

public class TestImportadorDispositivosInteligentes {

	static Dispositivos repoDispositivos = new Repositorio().dispositivos();
	static List<Dispositivo> dispositivos;

	@Before
	public void setUp() {

		repoDispositivos.cargarDispositivos("inteligente");
		dispositivos = repoDispositivos.getDispositivos();
		for (Dispositivo disp : dispositivos) {
			disp.presentate();
			// System.out.println(disp.getClass());
		}
	}

	@Test
	public void importacionEstandar() {

		assertEquals(16, dispositivos.size());

	}

}
