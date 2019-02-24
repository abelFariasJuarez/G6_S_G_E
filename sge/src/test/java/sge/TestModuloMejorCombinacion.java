package sge;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.ActuadorAhorro;
import sge.modelo.ActuadorApagar;
import sge.modelo.ActuadorPrender;
import sge.modelo.DispositivoFactoryMethod;
import sge.modelo.DriverBasico;
import sge.modelo.GestorCliente;
import sge.modelo.Recomendacion;
import sge.modelo.Repositorio;
import sge.modelo.RestriccionesHorasFamilia;
import sge.modelo.dispositivo.*;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.RestriccionHorasFamiliaVO;

public class TestModuloMejorCombinacion {

	private RestriccionHorasFamiliaVO rest_air;
	private RestriccionHorasFamiliaVO rest_lava;
	private RestriccionHorasFamiliaVO rest_venti;
	private RestriccionHorasFamiliaVO rest_pc;

	@Before
	public void setUpGeneral() throws Exception {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		rest_air = repositorio.restriccionesHorasFamilia().findBy("codigo", "AIRCONDITIONER");
		rest_lava = repositorio.restriccionesHorasFamilia().findBy("codigo", "WASHINGMACHINE");
		rest_venti = repositorio.restriccionesHorasFamilia().findBy("codigo", "FAN");
		rest_pc = repositorio.restriccionesHorasFamilia().findBy("codigo", "COMPUTER");
		repositorio.cerrar();

	}

	@Test
	public void testSistemaCompatibleDeterminado() {
		
		DispositivoFactoryMethod.cargaBasica();
		ClienteVO unCliente = new ClienteVO("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

		//DispositivoInteligente air = new DispositivoInteligente("heladera", 0.18, true, new DriverBasico());
		//DispositivoEstandar lava = new DispositivoEstandar("lavadora", 0.875, true);
		//DispositivoEstandar unVenti = new DispositivoEstandar("Ventilador", 0.06, true);
		//air.setRestriccionHoras(rest_air);
		//lava.setRestriccionHoras(rest_lava);
		//unVenti.setRestriccionHoras(rest_venti);
		DispositivoVO air = DispositivoFactoryMethod.getDispositivoByCode("Aire2200");
		air.setConsumoPorHora(0.18);
		
		DispositivoVO lava = DispositivoFactoryMethod.getDispositivoByCode("LavaSemiAuto");
		lava.setConsumoPorHora(0.875);
		
		DispositivoVO unVenti = DispositivoFactoryMethod.getDispositivoByCode("VentiladorPie");
		unVenti.setConsumoPorHora(0.06);

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
		ClienteVO unCliente = new ClienteVO("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

		DispositivoInteligenteVO air = new DispositivoInteligenteVO("heladera", 1000.0, true, new DriverBasico());
		DispositivoEstandarVO pc = new DispositivoEstandarVO("Computadora", 2000.0, true);
		DispositivoEstandarVO unVenti = new DispositivoEstandarVO("Ventilador", 3000.0, true);

		air.setRestriccionHoras(rest_air);
		pc.setRestriccionHoras(rest_pc);
		unVenti.setRestriccionHoras(rest_venti);

		unCliente.addDispositivo(air);
		unCliente.addDispositivo(pc);
		unCliente.addDispositivo(unVenti);

		Assert.assertFalse(unCliente.canYouGetMejorCombinacionDispositivos());
	}

	@Test
	public void mejorarEficienciaHogares() {

		ClienteVO cliente1 = new ClienteVO("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);
		cliente1.setAhorroAutomatico(true);

		DispositivoInteligenteVO air1 = new DispositivoInteligenteVO("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligenteVO lava1 = new DispositivoInteligenteVO("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligenteVO unVenti1 = new DispositivoInteligenteVO("Ventilador", 0.06, true, new DriverBasico());

		air1.setRestriccionHoras(rest_air);
		lava1.setRestriccionHoras(rest_lava);
		unVenti1.setRestriccionHoras(rest_venti);

		cliente1.addDispositivo(air1);
		cliente1.addDispositivo(lava1);
		cliente1.addDispositivo(unVenti1);
		air1.prender();
		lava1.prender();
		unVenti1.prender();

		ClienteVO cliente2 = new ClienteVO("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321013, 1543312311);
		cliente2.setAhorroAutomatico(true);

		DispositivoInteligenteVO air2 = new DispositivoInteligenteVO("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligenteVO lava2 = new DispositivoInteligenteVO("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligenteVO unVenti2 = new DispositivoInteligenteVO("Ventilador", 0.06, true, new DriverBasico());

		air2.setRestriccionHoras(rest_air);
		lava2.setRestriccionHoras(rest_lava);
		unVenti2.setRestriccionHoras(rest_venti);

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

		List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
		clienteVOs.add(cliente1);
		clienteVOs.add(cliente2);

		assertTrue(lava2.estoyON());
		unGestor.mejorarEficienciaHogaresA(clienteVOs);

		assertTrue(lava1.estoyON());
		assertTrue(lava2.estoyOFF());
	}

	@Test
	public void mejorarEficienciaHogaresNoActivoNoHacer() {

		ClienteVO cliente2 = new ClienteVO("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321013, 1543312311);
		cliente2.setAhorroAutomatico(false);

		DispositivoInteligenteVO air2 = new DispositivoInteligenteVO("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligenteVO lava2 = new DispositivoInteligenteVO("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligenteVO unVenti2 = new DispositivoInteligenteVO("Ventilador", 0.06, true, new DriverBasico());

		air2.setRestriccionHoras(rest_air);
		lava2.setRestriccionHoras(rest_lava);
		unVenti2.setRestriccionHoras(rest_venti);

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

		List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
		clienteVOs.add(cliente2);
		unGestor.mejorarEficienciaHogaresA(clienteVOs);

		assertTrue(lava2.estoyON());
	}

}
