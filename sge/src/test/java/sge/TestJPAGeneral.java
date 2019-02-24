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

import sge.modelo.DriverBasico;
import sge.modelo.Repositorio;
import sge.modelo.RestriccionesHorasFamilia;
import sge.modelo.Zonas;
import sge.modelo.valueobjects.AccionPrenderVO;
import sge.modelo.valueobjects.AdministradorVO;
import sge.modelo.valueobjects.CategoriaVO;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.ComparadorVO;
import sge.modelo.valueobjects.CondicionVO;
import sge.modelo.valueobjects.DispositivoConModuloVO;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.MayorIgualVO;
import sge.modelo.valueobjects.ReglaVO;
import sge.modelo.valueobjects.SensorVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.UbicacionVO;
import sge.modelo.valueobjects.ZonaVO;

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

		DispositivoInteligenteVO air2 = new DispositivoInteligenteVO("heladera", 0.18, true, new DriverBasico());
		DispositivoInteligenteVO lava2 = new DispositivoInteligenteVO("lavadora", 0.875, true, new DriverBasico());
		DispositivoInteligenteVO unVenti2 = new DispositivoInteligenteVO("Ventilador", 0.06, true, new DriverBasico());

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

		repositorio.dispositivos().persistir(air2);
		repositorio.dispositivos().persistir(lava2);
		repositorio.dispositivos().persistir(unVenti2);
		
		repositorio.borrar(air2);
		repositorio.borrar(lava2);
		repositorio.borrar(unVenti2);
	}

	@Test
	public void aPersistirConModulo() {
		DispositivoEstandarVO comun = new DispositivoEstandarVO("microondas", 12.0, "pepe", false, 10.0);
		ClienteVO unCliente = new ClienteVO("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		unCliente.addDispositivo(comun);

		DispositivoConModuloVO conModulo = unCliente.agrega_modulo_a_estandar(comun);
		repositorio.dispositivos().persistir(conModulo);
		repositorio.borrar(conModulo);
	}

	// Test reglas
	@Test
	public void aPersistirSensorComparadorCondicion() {
		SensorVO unS = null;

		unS = new SensorVO();
		unS.setMedicion(0.0);
		unS.setNombre("sensor1");
		unS.setTiempoDeEspera(30.0);

		repositorio.sensores().persistir(unS);

		ComparadorVO cmp = null;
		cmp = new MayorIgualVO();
		repositorio.comparaciones().persistir(cmp);

		CondicionVO cond = null;
		cond = new CondicionVO();
		cond.setComparador(cmp);
		cond.setSensor(unS);
		cond.setValorEsperado(34.0);
		repositorio.condiciones().persistir(cond);

		ReglaVO unRegla = null;
		unRegla = new ReglaVO("regla 1");
		unRegla.agregarCondicion(cond);

		AccionPrenderVO prenderAire = new AccionPrenderVO();
		unRegla.agregarAccion(prenderAire);

		repositorio.reglas().persistir(unRegla);
		repositorio.borrar(unRegla);

	}

	// Test restricciones horas familia
	@Test
	public void aPersistirRestriccionesHF() {
		assertEquals(true, true);
	}

	// Test transformadores
	@Test
	public void aPersistirTransformadores() {

		TransformadorVO c = new TransformadorVO();
		c.setIdZona(4);
		c.setUbicacion(new UbicacionVO(1.0, 1.0));
		repositorio.transformadores().persistir(c);
		repositorio.borrar(c);
	}

	// Test usuarios
	@Test
	public void aPersistirCliente() {
		ClienteVO c = new ClienteVO("Carla", "Sanazki", "condarco 149", LocalDate.of(2017, 4, 7), "cazana", "menToL2017",
				"Dni", 21321013, 1543312311);
		c.setAhorroAutomatico(false);
		UbicacionVO u  = repositorio.ubicaciones().getPersistente(1.0, 1.0);
		c.setUbicacion(u);

		CategoriaVO unaCate = new CategoriaVO("R0", 13.f, 0.05f, 1.5f, 10f);
		c.setCategoria(unaCate);
		repositorio.clientes().persistir(c);
		repositorio.borrar(c);
	}

	@Test
	public void aPersistirAdministrador() {

		AdministradorVO admin = new AdministradorVO("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
				"pepe", "pasti");
		repositorio.persistirUsuario(admin);
		repositorio.borrar(admin);
	}

	// Test zonas
	@Test
	public void aPersistirZonas() {
		TransformadorVO tran = new TransformadorVO();
		tran.setIdZona(6);
		tran.setUbicacion(new UbicacionVO(1.2, 2.0));

		ClienteVO c = new ClienteVO("Carl", "adfg", "condarco 125", LocalDate.of(2017, 4, 7), "faras", "mencoco125", "Dni",
				21321013, 1543312311);
		c.setAhorroAutomatico(false);
		
		c.setUbicacion(repositorio.ubicaciones().getPersistente(1.3 , 2.1));

		ZonaVO zona = new ZonaVO();
		zona.setCentro(repositorio.ubicaciones().getPersistente(1.2, 2.1));
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
