package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import sge.dispositivo.*;
import sge.driver.DriverBasico;
import sge.regla.ActuadorAhorro;
import sge.regla.ActuadorApagar;
import sge.regla.ActuadorPrender;

public class TestDispositivoInteligente {

	@Test
	public void unaHoraPrendido() {
		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, true,driver);
		// el constructor ya me da un dispo en estado prendido
		unDispo.apagar();

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.intervalos.get(0).setInicio(desde);
		unDispo.intervalos.get(0).setFin(hasta);

		assertEquals(2.3, unDispo.consumo_periodo(desde, hasta), 0);
	}

	@Test
	public void mediaHoraApagadoMediaHoraPrendido() {
		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"perez",false, false,driver);
		// el constructor ya me da un dispo en estado apagado
		unDispo.prender();

		LocalDateTime desde_apagado = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta_apagado = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime desde_prendido = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime hasta_prendido = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.intervalos.get(0).setInicio(desde_apagado);
		unDispo.intervalos.get(0).setFin(hasta_apagado);
		unDispo.intervalos.get(1).setInicio(desde_prendido);
		unDispo.intervalos.get(1).setFin(hasta_prendido);

		assertEquals(1.15, unDispo.consumo_periodo(desde_apagado, hasta_prendido), 0);
	}

	@Test
	public void mediaHoraAhorroEnergiaMediaHoraPrendido() {
		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, false,driver);
		// el constructor ya me da un dispo en estado apagado
		unDispo.ahorroDeEnergia();
		unDispo.prender();

		LocalDateTime desde_apagado = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta_apagado = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime desde_prendido = LocalDateTime.parse("2018-05-19T20:30:00.775887700");
		LocalDateTime hasta_prendido = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.intervalos.get(1).setInicio(desde_apagado);
		unDispo.intervalos.get(1).setFin(hasta_apagado);
		unDispo.intervalos.get(2).setInicio(desde_prendido);
		unDispo.intervalos.get(2).setFin(hasta_prendido);

		assertEquals((2.3 * (1.0 + 0.7) * 0.5), unDispo.consumo_periodo(desde_apagado, hasta_prendido), 0);

	}

	@Test
	public void prendidoPeroNoUltimaHora() {
		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera", 2.3,"pepe",false, false,driver);
		// el constructor ya me da un dispo en estado prendido


		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");

		/* codigo duro solo para poder hacer el test Abel Farias */
		unDispo.intervalos.get(0).setInicio(desde);
		unDispo.intervalos.get(0).setFin(hasta);

		assertEquals(0.0, unDispo.consumo_ultimas_n_horas(1), 0);
	}

}
