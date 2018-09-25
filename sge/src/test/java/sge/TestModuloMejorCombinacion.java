package sge;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sge.modelo.dispositivo.*;
import sge.modelo.driver.DriverBasico;
import sge.modelo.hogareficiente.Recomendacion;
import sge.modelo.regla.ActuadorAhorro;
import sge.modelo.regla.ActuadorApagar;
import sge.modelo.regla.ActuadorPrender;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.GestorCliente;
import sge.repositorios.RepositorioRestriccionHorasFamilia;

public class TestModuloMejorCombinacion {
	@Test
	public void testSistemaCompatibleDeterminado() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);
		DriverBasico driver = new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente air = new DispositivoInteligente("heladera", 0.18, true, driver);
		DispositivoEstandar lava = new DispositivoEstandar("lavadora", 0.875, true);
		DispositivoEstandar unVenti = new DispositivoEstandar("Ventilador", 0.06, true);

		air.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		lava.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "WASHINGMACHINE"));
		unVenti.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));

		unCliente.addDispositivo(air);
		unCliente.addDispositivo(lava);
		unCliente.addDispositivo(unVenti);

		Recomendacion reco = unCliente.getMejorCombinacionDispositivos();

		Assert.assertEquals(360, reco.horasMaximasPara(air), 0.01);
		Assert.assertEquals(30, reco.horasMaximasPara(lava), 0.01);
		Assert.assertEquals(360, reco.horasMaximasPara(unVenti), 0.01);

	}

	@Test
	public void canYouGetMejorCombinacionDispositivosFalse() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);
		DriverBasico driver = new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente air = new DispositivoInteligente("heladera", 1000.0, true, driver);
		DispositivoEstandar pc = new DispositivoEstandar("Computadora", 2000.0, true);
		DispositivoEstandar unVenti = new DispositivoEstandar("Ventilador", 3000.0, true);
		
		
		air.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		pc.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "COMPUTER"));
		unVenti.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));

		unCliente.addDispositivo(air);
		unCliente.addDispositivo(pc);
		unCliente.addDispositivo(unVenti);

		Assert.assertFalse(unCliente.canYouGetMejorCombinacionDispositivos());
	}

	@Test
	public void mejorarEficienciaHogares() {

		Cliente cliente1 = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);
		cliente1.setAhorroAutomatico(true);
		DriverBasico driver = new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente air1 = new DispositivoInteligente("heladera", 0.18, true, driver);
		DispositivoInteligente lava1 = new DispositivoInteligente("lavadora", 0.875, true, driver);
		DispositivoInteligente unVenti1 = new DispositivoInteligente("Ventilador", 0.06, true, driver);

		air1.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		lava1.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "WASHINGMACHINE"));
		unVenti1.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));

		cliente1.addDispositivo(air1);
		cliente1.addDispositivo(lava1);
		cliente1.addDispositivo(unVenti1);
		air1.prender();
		lava1.prender();
		unVenti1.prender();

		Cliente cliente2 = new Cliente("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321013, 1543312311);
		cliente2.setAhorroAutomatico(true);

		DispositivoInteligente air2 = new DispositivoInteligente("heladera", 0.18, true, driver);
		DispositivoInteligente lava2 = new DispositivoInteligente("lavadora", 0.875, true, driver);
		DispositivoInteligente unVenti2 = new DispositivoInteligente("Ventilador", 0.06, true, driver);

		air2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		lava2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "WASHINGMACHINE"));
		unVenti2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));
		
		cliente2.addDispositivo(air2);
		cliente2.addDispositivo(lava2);
		cliente2.addDispositivo(unVenti2);
		air2.prender();
		lava2.prender();
		unVenti2.prender();
		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		/*
		 * codigo duro solo para poder hacer el test, esto va a hacer que el lavaropas
		 * este prendido desde el 19/05/2018, es decir muchas horas prendio Abel Farias
		 */
		lava2.getIntervalos().get(0).setInicio(desde);

		GestorCliente unGestor = new GestorCliente();

		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);

		assertTrue(lava2.estoyON());
		unGestor.mejorarEficienciaHogaresA(clientes);

		assertTrue(lava1.estoyON());
		assertTrue(lava2.estoyOFF());
	}

	@Test
	public void mejorarEficienciaHogaresNoActivoNoHacer() {

		Cliente cliente2 = new Cliente("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321013, 1543312311);
		cliente2.setAhorroAutomatico(false);
		DriverBasico driver = new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente air2 = new DispositivoInteligente("heladera", 0.18, true, driver);
		DispositivoInteligente lava2 = new DispositivoInteligente("lavadora", 0.875, true, driver);
		DispositivoInteligente unVenti2 = new DispositivoInteligente("Ventilador", 0.06, true, driver);

		air2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		lava2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "WASHINGMACHINE"));
		unVenti2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));
	
		cliente2.addDispositivo(air2);
		cliente2.addDispositivo(lava2);
		cliente2.addDispositivo(unVenti2);
		air2.prender();
		lava2.prender();
		unVenti2.prender();
		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		/*
		 * codigo duro solo para poder hacer el test, esto va a hacer que el lavaropas
		 * este prendido desde el 19/05/2018, es decir muchas horas prendio Abel Farias
		 */
		lava2.getIntervalos().get(0).setInicio(desde);

		GestorCliente unGestor = new GestorCliente();

		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente2);
		unGestor.mejorarEficienciaHogaresA(clientes);

		assertTrue(lava2.estoyON());
	}

}
