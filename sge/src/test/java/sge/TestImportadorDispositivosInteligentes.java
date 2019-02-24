package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sge.modelo.Repositorio;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;

public class TestImportadorDispositivosInteligentes {

	static Dispositivos repoDispositivos = new Repositorio().dispositivos();
	static List<DispositivoVO> dispositivos;

	@Before
	public void setUp() {

		repoDispositivos.cargarDispositivos("inteligente");
		dispositivos = repoDispositivos.getDispositivos();
		for (DispositivoVO disp : dispositivos) {
			disp.presentate();
			// System.out.println(disp.getClass());
		}
	}

	@Test
	public void importacionEstandar() {

		assertEquals(16, dispositivos.size());

	}

}
