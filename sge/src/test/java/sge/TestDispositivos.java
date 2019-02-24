package sge;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.Repositorio;
import sge.modelo.dispositivo.*;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoVO;

public class TestDispositivos {

	/* por que funciona solo con static? hay algo mal en los repo? */
	static Dispositivos repoDispositivos = new Repositorio().dispositivos();
	static List<DispositivoVO> Dispositivos;

	@BeforeClass
	public static void setUp() {
		repoDispositivos.cargarDispositivos("todos");
		Dispositivos = repoDispositivos.getDispositivos();

		for (DispositivoVO disp : Dispositivos) {
			disp.presentate();
		}

	}

	@Test
	public void hayDispositivos() {
		assertEquals(false, Dispositivos.isEmpty());

	}

	@Test
	public void ConsumoDispositivoEstandar() {
		DispositivoEstandarVO disestandar = new DispositivoEstandarVO("microondas", 12.0, "perez", false, 12.5);
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T20:30:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		// seteo aproximado del cliente
		disestandar.setHorasEncendidoPorDia(9.0);
		assertEquals(3348.0, disestandar.consumo_periodo(desde, hasta), 0);
	}

}
