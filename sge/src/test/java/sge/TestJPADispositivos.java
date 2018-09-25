package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.DriverBasico;
import sge.modelo.regla.ActuadorAhorro;
import sge.modelo.regla.ActuadorApagar;
import sge.modelo.regla.ActuadorPrender;
import sge.repositorios.Repositorio;
import sge.repositorios.RepositorioDeDispositivos;
import sge.repositorios.RepositorioRestriccionHorasFamilia;

public class TestJPADispositivos {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = RepositorioDeDispositivos.getinstance();
		repositorio.abrir();
	}

	@Test
	public void aPersistirInteligentes() {	
		DriverBasico driver = new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente air2 = new DispositivoInteligente("heladera", 0.18, true, driver);
		DispositivoInteligente lava2 = new DispositivoInteligente("lavadora", 0.875, true, driver);
		DispositivoInteligente unVenti2 = new DispositivoInteligente("Ventilador", 0.06, true, driver);

		air2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "AIRCONDITIONER"));
		lava2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "WASHINGMACHINE"));
		unVenti2.setRestriccionHoras(RepositorioRestriccionHorasFamilia.getinstance().findBy("codigo", "FAN"));

		air2.prender();
		lava2.prender();
		unVenti2.prender();
		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
		/*
		 * codigo duro solo para poder hacer el test, esto va a hacer que el lavaropas
		 * este prendido desde el 19/05/2018, es decir muchas horas prendio Abel Farias
		 */
		lava2.getIntervalos().get(0).setInicio(desde);

		
		repositorio.persistir(air2);
		repositorio.persistir(lava2);
		repositorio.persistir(unVenti2);
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}
}
