package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import sge.modelo.dispositivo.*;
import sge.modelo.driver.ActuadorAhorro;
import sge.modelo.driver.ActuadorApagar;
import sge.modelo.driver.ActuadorPrender;
import sge.modelo.driver.DriverBasico;

public class TestDispositivoInteligente {

	@Test
	public void unaHoraPrendido() {
		
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, true,new DriverBasico());
		// el constructor ya me da un dispo en estado prendido
		unDispo.apagar();

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.getIntervalos().get(0).setInicio(desde);
		unDispo.getIntervalos().get(0).setFin(hasta);

		assertEquals(2.3, unDispo.consumo_periodo(desde, hasta), 0);
	}

	@Test
	public void mediaHoraApagadoMediaHoraPrendido() {
		
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"perez",false, false,new DriverBasico());
		// el constructor ya me da un dispo en estado apagado
		unDispo.prender();

		LocalDateTime desde_apagado = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta_apagado = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime desde_prendido = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime hasta_prendido = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.getIntervalos().get(0).setInicio(desde_apagado);
		unDispo.getIntervalos().get(0).setFin(hasta_apagado);
		unDispo.getIntervalos().get(1).setInicio(desde_prendido);
		unDispo.getIntervalos().get(1).setFin(hasta_prendido);

		assertEquals(1.15, unDispo.consumo_periodo(desde_apagado, hasta_prendido), 0);
	}

	@Test
	public void mediaHoraAhorroEnergiaMediaHoraPrendido() {

		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, false,new DriverBasico());
		// el constructor ya me da un dispo en estado apagado
		unDispo.ahorroDeEnergia();
		unDispo.prender();

		LocalDateTime desde_apagado = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta_apagado = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime desde_prendido = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime hasta_prendido = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.getIntervalos().get(1).setInicio(desde_apagado);
		unDispo.getIntervalos().get(1).setFin(hasta_apagado);
		unDispo.getIntervalos().get(2).setInicio(desde_prendido);
		unDispo.getIntervalos().get(2).setFin(hasta_prendido);

		assertEquals((2.3 * (1.0 + 0.7) * 0.5), unDispo.consumo_periodo(desde_apagado, hasta_prendido), 0);

	}

	@Test
	public void prendidoPeroNoUltimaHora() {
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, false,new DriverBasico());
		// el constructor ya me da un dispo en estado prendido


		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.getIntervalos().get(0).setInicio(desde);
		unDispo.getIntervalos().get(0).setFin(hasta);

		assertEquals(0.0, unDispo.consumo_ultimas_n_horas(1), 0);
	}

}
