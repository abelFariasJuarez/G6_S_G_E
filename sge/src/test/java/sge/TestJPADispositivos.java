package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.DispositivoConModulo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.ActuadorAhorro;
import sge.modelo.driver.ActuadorApagar;
import sge.modelo.driver.ActuadorPrender;
import sge.modelo.driver.DriverBasico;
import sge.modelo.usuarios.Cliente;
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
		
		DispositivoInteligente air2 = new DispositivoInteligente("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligente lava2 = new DispositivoInteligente("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligente unVenti2 = new DispositivoInteligente("Ventilador", 0.06, true, new DriverBasico());

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
	
	@Test
	public void aPersistirConModulo() {
		DispositivoEstandar comun = new DispositivoEstandar("microondas", 12.0, "pepe",false,10.0);
		Cliente unCliente = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		unCliente.addDispositivo(comun);
		
		DispositivoConModulo conModulo = unCliente.agrega_modulo_a_estandar(comun);
		repositorio.persistir(conModulo);
	}

	@After	
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}
}
