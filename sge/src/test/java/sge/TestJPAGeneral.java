package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import sge.modelo.dispositivo.DispositivoConModulo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.DriverBasico;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.regla.AccionPrender;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;
import sge.modelo.regla.comparador.MayorIgual;
import sge.modelo.usuarios.Administrador;
import sge.modelo.usuarios.Categoria;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;
import sge.repositorios.Zonas;
import sge.repositorios.RestriccionesHorasFamilia;

public class TestJPAGeneral extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private Repositorio repositorio;

	@Before
	public void setUpGeneral() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	// Test conexion base local
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {
		});
	}

	// Test dispositivos
	@Test
	public void aPersistirInteligentes() {

		DispositivoInteligente air2 = new DispositivoInteligente("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligente lava2 = new DispositivoInteligente("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligente unVenti2 = new DispositivoInteligente("Ventilador", 0.06, true, new DriverBasico());

		air2.setRestriccionHoras(repositorio.restriccionesHorasFamilia().findBy("codigo", "AIRCONDITIONER"));
		lava2.setRestriccionHoras(repositorio.restriccionesHorasFamilia().findBy("codigo", "WASHINGMACHINE"));
		unVenti2.setRestriccionHoras(repositorio.restriccionesHorasFamilia().findBy("codigo", "FAN"));

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
		
		repositorio.borrar(air2);
		repositorio.borrar(lava2);
		repositorio.borrar(unVenti2);

	}

	@Test
	public void aPersistirConModulo() {
		DispositivoEstandar comun = new DispositivoEstandar("microondas", 12.0, "pepe", false, 10.0);
		Cliente unCliente = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		unCliente.addDispositivo(comun);

		DispositivoConModulo conModulo = unCliente.agrega_modulo_a_estandar(comun);
		repositorio.persistir(conModulo);
		repositorio.borrar(conModulo);
	}

	// Test reglas
	@Test
	public void aPersistirSensorComparadorCondicion() {
		Sensor unS = null;
		unS = new Sensor();
		unS.setMedicion(0.0);
		unS.setNombre("sensor1");
		unS.setTiempoDeEspera(30.0);

		repositorio.persistir(unS);

		Comparador cmp = null;
		cmp = new MayorIgual();
		repositorio.persistir(cmp);

		Condicion cond = null;
		cond = new Condicion();
		cond.setComparador(cmp);
		cond.setSensor(unS);
		cond.setValorEsperado(34.0);
		repositorio.persistir(cond);

		Regla unRegla = null;
		unRegla = new Regla("regla 1");
		unRegla.agregarCondicion(cond);

		AccionPrender prenderAire = new AccionPrender();
		unRegla.agregarAccion(prenderAire);

		repositorio.persistir(unRegla);
		repositorio.borrar(unRegla);

	}

	// Test transformadores
	@Test
	public void aPersistirTransformadores() {

		Transformador c = new Transformador();
		c.setIdZona(4);
		c.setUbicacion(new Ubicacion(1.0, 1.0));
		repositorio.persistir(c);
		repositorio.borrar(c);
	}

	// Test usuarios
	@Test
	public void aPersistirCliente() {
		Cliente c = new Cliente("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana", "menToL2017",
				"Dni", 21321013, 1543312311);
		c.setAhorroAutomatico(false);
		c.setUbicacion(new Ubicacion(1.0, 1.0));

		Categoria unaCate = new Categoria("R0", 13.f, 0.05f, 1.5f, 10f);
		c.setCategoria(unaCate);
		repositorio.persistir(c);
		repositorio.borrar(c);
	}

	@Test
	public void aPersistirAdministrador() {

		Administrador admin = new Administrador("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
				"pepe", "pasti");
		repositorio.persistir(admin);
		repositorio.borrar(admin);
	}

	// Test zonas
	@Test
	public void aPersistirZonas() {
		Transformador tran = new Transformador();
		tran.setIdZona(6);
		tran.setUbicacion(new Ubicacion(1.2, 2.0));

		Cliente c = new Cliente("Carl", "adfg", "condarco 125", LocalDate.of(2017, 4, 7), "faras", "mencoco125", "Dni",
				21321013, 1543312311);
		c.setAhorroAutomatico(false);
		c.setUbicacion(new Ubicacion(1.3, 2.1));

		ZonaGeografica zona = new ZonaGeografica();
		zona.setCentro(new Ubicacion(1.2, 2.0));
		zona.addCliente(c);
		zona.setId(125);
		zona.setNombre("zona1");
		zona.setRadio((float) 12.3);
		zona.Add(tran);

		repositorio.zonas().persistir(zona);
		repositorio.borrar(zona);
	}

	@After
	public void tearDownGeneral() throws Exception {
		repositorio.cerrar();
	}

}
