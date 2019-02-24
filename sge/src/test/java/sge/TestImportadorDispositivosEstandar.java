package sge;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import sge.modelo.Repositorio;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;

public class TestImportadorDispositivosEstandar {

	Dispositivos repoDispositivos = new Repositorio().dispositivos();
	List<DispositivoVO> dispositivos;

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
