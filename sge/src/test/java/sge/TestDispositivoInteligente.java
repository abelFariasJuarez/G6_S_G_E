package sge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class TestDispositivoInteligente {

	@Test
	public void unaHoraPrendido() {

		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3, true);
		Intervalo inter1 = new Intervalo();
		unDispo.addIntervalo(inter1);

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		inter1.setInicio(desde);
		inter1.setFin(hasta);
		inter1.setEstado(new EstadoPrendido());

		assertEquals(2.3, unDispo.consumo_periodo(desde, hasta), 0);
	}

	@Test
	public void mediaHoraApagadoMediaHoraPrendido() {
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3, true);
		Intervalo inter1 = new Intervalo();
		unDispo.addIntervalo(inter1);

		Intervalo inter2 = new Intervalo();
		unDispo.addIntervalo(inter2);

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		inter1.setInicio(desde);
		inter1.setFin(LocalDateTime.parse("2018-05-19T20:30:00.775887700"));
		inter1.setEstado(new EstadoPrendido());

		inter2.setInicio(LocalDateTime.parse("2018-05-19T20:30:00.775887700"));
		inter2.setFin(hasta);
		inter2.setEstado(new EstadoApagado());

		assertTrue(1.15 == unDispo.consumo_periodo(desde, hasta));
	}

	@Test
	public void mediaHoraAhorroEnergiaMediaHoraPrendido() {
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3, true);
		Intervalo inter1 = new Intervalo();
		unDispo.addIntervalo(inter1);

		Intervalo inter2 = new Intervalo();
		unDispo.addIntervalo(inter2);

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		inter1.setInicio(desde);
		inter1.setFin(LocalDateTime.parse("2018-05-19T20:30:00.775887700"));
		inter1.setEstado(new EstadoPrendido());

		inter2.setInicio(LocalDateTime.parse("2018-05-19T20:30:00.775887700"));
		inter2.setFin(hasta);
		inter2.setEstado(new EstadoAhorroDeEnergia());

		assertTrue((2.3 * (1.0 + 0.7) * 0.5) == unDispo.consumo_periodo(desde, hasta));

	}

	@Test
	public void prendidoPeroNoUltimaHora() {
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3, true);
		Intervalo inter1 = new Intervalo();
		unDispo.addIntervalo(inter1);

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		inter1.setInicio(desde);
		inter1.setFin(hasta);
		inter1.setEstado(new EstadoPrendido());

		assertEquals(0.0, unDispo.consumo_ultimas_n_horas(1), 0);
	}

}
