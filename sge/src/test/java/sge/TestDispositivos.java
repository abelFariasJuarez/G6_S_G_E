package sge;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.dispositivo.*;
import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;

public class TestDispositivos {

	static Dispositivos repoDispositivos = Repositorio.getInstance().dispositivos();
	static List<Dispositivo> Dispositivos;

	@BeforeClass
	public static void setUp() {
		repoDispositivos.cargarDispositivos("todos");
		Dispositivos = repoDispositivos.getDispositivos();

		for (Dispositivo disp : Dispositivos) {
			disp.presentate();
		}

	}

	@Test
	public void hayDispositivos() {
		assertEquals(false, Dispositivos.isEmpty());

	}

	@Test
	public void ConsumoDispositivoEstandar() {
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0, "perez", false, 12.5);
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T20:30:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		// seteo aproximado del cliente
		disestandar.setHorasEncendidoPorDia(9.0);
		assertEquals(3348.0, disestandar.consumo_periodo(desde, hasta), 0);
	}

}
