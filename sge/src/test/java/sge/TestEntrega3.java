package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.DriverBasico;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.AccionPrender;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;
import sge.modelo.regla.comparador.MayorIgual;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;

public class TestEntrega3 {
	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	@Test
	public void casoDePrueba1() {

		// Caso de prueba 1: Crear 1 usuario nuevo.
		this.crearUsuarioDelCasoDePrueba();		
		Cliente clienteOriginal = repositorio.clientes().findBy("username", "fperez");
		
		// Persistirlo.
		repositorio.persistir(clienteOriginal);

		// Recuperarlo,
		Cliente clienteRecuperado = repositorio.clientes().findBy("username", "fperez");

		// modificar la geolocalización
		clienteRecuperado.getUbicacion().setLongitud(130.0);

		// y grabarlo.
		repositorio.persistir(clienteRecuperado);

		// Recuperarlo
		Cliente clienteRecuperado2 = repositorio.clientes().findBy("username", "fperez");

		// y evaluar que el cambio se haya realizado.
		assertEquals(clienteRecuperado2.getUbicacion().getLongitud(), 130.0, 0);
	}

	@Test
	public void casoDePrueba2() {
		// Caso de prueba 2:
		this.crearDispositivoDelCasoDePrueba();

		// Recuperar un dispositivo.
		DispositivoInteligente unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");

		// Mostrar por consola todos los intervalos que estuvo
		// encendido durante el último mes.
		unDispo.presentate();
		unDispo.mostrarIntervalosEncendidos();

		// Modificar su nombre (o cualquier otro atributo editable)
		unDispo.setBajoconsumo(false);

		// y grabarlo.
		repositorio.persistir(unDispo);

		// Recuperarlo
		DispositivoInteligente unDispoRecuperado = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");

		// y evaluar que el nombre coincida con el esperado.
		assertEquals(unDispoRecuperado.getBajoconsumo(), false);
	}

	@Test
	public void casoDePrueba3() {
		
		this.crearDispositivoDelCasoDePrueba();
		DispositivoInteligente unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		unDispo.apagar();
		
		// Caso de prueba 3:
		// Crear una nueva regla.

		// Asociarla a un dispositivo.
		// Agregar condiciones
		// y acciones.
		Sensor unS = repositorio.sensores().findBy("nombre", "sensorCasoPrueba3");
		if (unS == null) {
			unS = new Sensor();
		}

		Comparador cmp = repositorio.comparaciones().findBy("oid", 1L);
		if (cmp == null) {
			cmp = new MayorIgual();
		}

		Condicion cond = repositorio.condiciones().findBy("oid", 1L);
		if (cond == null) {
			cond = new Condicion();
		}

		Condicion cond2 = repositorio.condiciones().findBy("oid", 2L);
		if (cond2 == null) {
			cond2 = new Condicion();
		}

		unS.setMedicion(40.0);
		unS.setNombre("sensorCasoPrueba3");
		unS.setTiempoDeEspera(20.0);

		cond.setComparador(cmp);
		cond.setSensor(unS);
		cond.setValorEsperado(34.0);

		cond2.setComparador(cmp);
		cond2.setSensor(unS);
		cond2.setValorEsperado(36.0);
		
		Regla unRegla = repositorio.reglas().findBy("name", "reglaCasoPrueba3");
		if (unRegla == null) {
			unRegla = new Regla("reglaCasoPrueba3");
			unRegla.agregarCondicion(cond);
			unRegla.agregarCondicion(cond2);
			unRegla.agregarAccion(new AccionPrender());
		}		

		// Persistirla.		
		repositorio.persistir(unRegla);

		// Recuperarla
		Regla unReglaRecuperada = repositorio.reglas().findBy("name", "reglaCasoPrueba3");

		// y ejecutarla.
		assertEquals(unDispo.estoyON(), false);
		unReglaRecuperada.accionarSiCorresponde(unDispo);
		assertEquals(unDispo.estoyON(), true);

		// Modificar alguna condición
		cond2.setValorEsperado(20.0);

		// y persistirla.
		repositorio.persistir(cond2);

		// Recuperarla
		Condicion cond2Recuperada = repositorio.condiciones().findBy("oid", cond2.getOid());

		// y evaluar que la condición modificada posea la última modificación.
		assertEquals(cond2Recuperada.getValorEsperado(), 20.0, 0.0);
	}

	@Test
	public void casoDePrueba4() {
		// Caso de prueba 4:
		
		// Agregar una instancia de Transformador al JSON de entradas. ***altere el orden de los pasos del caso de prueba
		// Ejecutar el método de lectura y persistencia. ***altere el orden de los pasos del caso de prueba
		repositorio.transformadores().cargarTransformadores();
		repositorio.transformadores().guardarTransforamdores();
		
		// Recuperar todos los transformadores persistidos.
		List<Transformador> ts = repositorio.transformadores().buscarTodos(); 
		
		// Registrar la cantidad.		
		Integer cantidadT1 = ts.size();
		Integer cantidadT2 = repositorio.transformadores().getTransformadores().size();
		
		// Evaluar que la cantidad actual sea la anterior + 1. ***original
		// Evaluar que la cantidad de tranformadores en la BD sea igual ***reinterpretado
		// a la cantidad de tranformadores leidos del json ***reinterpretado
		assertEquals(cantidadT1, cantidadT2, 0);
	}
	
	@Test
	public void casoDePrueba5() {

		// Caso de prueba 5:
		LocalDateTime instanteDesde = LocalDateTime.parse("2018-05-15T10:00:00.775887700");
		LocalDateTime instanteHasta = LocalDateTime.parse("2018-05-30T23:00:00.775887700");
		
		// Dado un dispositivo y un período, mostrar por consola su
		// consumo promedio.
		this.crearDispositivoDelCasoDePrueba();
		DispositivoInteligente unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");	

		Double consumoDispositivo = unDispo.consumo_periodo(instanteDesde, instanteHasta);		
		System.out.println("\tconsumo promedio: " + consumoDispositivo + "del dispositivo en el periodo");
		
		// Dado un hogar y un período, mostrar por consola (interfaz de comandos) el
		// consumo total.
		this.crearUsuarioDelCasoDePrueba();		
		Cliente unCliente = repositorio.clientes().findBy("username", "fperez");	
		unCliente.addDispositivo(unDispo);
		Double consumoCliente =unCliente.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio: " + consumoCliente + "del cliente en el periodo");
		assertEquals(consumoDispositivo, consumoCliente, 0);
		
		// Dado un transformador y un período, mostrar su consumo promedio. 
		repositorio.transformadores().cargarTransformadores();
		repositorio.transformadores().guardarTransforamdores();
		Transformador unTrans = repositorio.transformadores().findBy("oid", 1L);
		unTrans.addCliente(unCliente);		
		Double consumoTrans = unTrans.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio: " + consumoTrans + "del Transformador en el periodo");
		assertEquals(consumoDispositivo, consumoTrans, 0);
		
		// Recuperar un dispositivo asociado a un hogar de ese transformador e
		// incrementar un 1000 % el consumo para ese período. 
		DispositivoInteligente unDispoRecu = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		unDispoRecu.setConsumoPorHora(unDispoRecu.getConsumoPorHora() * 1000);		
		// Persistir el dispositivo.
		repositorio.persistir(unDispoRecu);
		
		// Nuevamente mostrar el consumo para ese transformador.
		Double consumoTrans1000 = unTrans.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio * 1000 : " + consumoTrans + "del Transformador en el periodo");
		assertEquals(consumoDispositivo * 1000, consumoTrans1000, 0);
		
		unCliente.removeDispositivo(unDispo);
		repositorio.persistir(unCliente);
		
		unTrans.removeCliente(unCliente);
		repositorio.persistir(unTrans);
		
	}

	private void crearDispositivoDelCasoDePrueba() {
		DispositivoInteligente unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		if (unDispo == null) {
			unDispo = new DispositivoInteligente("DispoCasoPrueba", 7.1, true, new DriverBasico());

			unDispo.prender();
			unDispo.apagar();
			unDispo.prender();
			unDispo.apagar();
			unDispo.prender();
			unDispo.apagar();
			
			repositorio.persistir(unDispo);	
		}

		unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		
		if (unDispo != null) {			

			/* codigo duro solo para poder hacer el test Abel Farias */
			LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.775887700");
			LocalDateTime hasta = LocalDateTime.parse("2018-05-19T21:00:00.775887700");
			unDispo.getIntervalos().get(0).setInicio(desde);
			unDispo.getIntervalos().get(0).setFin(hasta);
			
			desde = LocalDateTime.parse("2018-05-19T21:00:00.775887701");
			hasta = LocalDateTime.parse("2018-05-20T10:00:00.775887700");
			unDispo.getIntervalos().get(1).setInicio(desde);
			unDispo.getIntervalos().get(1).setFin(hasta);
			
			desde = LocalDateTime.parse("2018-05-20T10:00:00.775887701");
			hasta = LocalDateTime.parse("2018-05-21T15:00:00.775887700");
			unDispo.getIntervalos().get(2).setInicio(desde);
			unDispo.getIntervalos().get(2).setFin(hasta);

			desde = LocalDateTime.parse("2018-05-21T15:00:00.775887701");
			hasta = LocalDateTime.parse("2018-05-21T23:00:00.775887700");
			unDispo.getIntervalos().get(3).setInicio(desde);
			unDispo.getIntervalos().get(3).setFin(hasta);

			desde = LocalDateTime.parse("2018-05-21T23:00:00.775887701");
			hasta = LocalDateTime.parse("2018-05-22T08:00:00.775887700");
			unDispo.getIntervalos().get(4).setInicio(desde);
			unDispo.getIntervalos().get(4).setFin(hasta);

			desde = LocalDateTime.parse("2018-05-22T08:00:00.775887701");
			hasta = LocalDateTime.parse("2018-05-22T19:00:00.775887700");
			unDispo.getIntervalos().get(5).setInicio(desde);
			unDispo.getIntervalos().get(5).setFin(hasta);

			unDispo.setBajoconsumo(true);
			unDispo.setConsumoPorHora(7.1);
			repositorio.persistir(unDispo);
		}

		
	}
	
	private void crearUsuarioDelCasoDePrueba() {
		Cliente clienteOriginal = repositorio.clientes().findBy("username", "fperez");
		if (clienteOriginal == null) {
			clienteOriginal = new Cliente("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez",
					"menToL2017", "Dni", 11222333, 1543312311);
			clienteOriginal.setAhorroAutomatico(false);
			clienteOriginal.setUbicacion(new Ubicacion(10.0, 90.0));
		}
		else
		{
			clienteOriginal.getUbicacion().setLongitud(90.0);	
		}
		
		repositorio.persistir(clienteOriginal);
	}	

	@Test
	public void reporte_consumo_hogar_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,10,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,10,28,20,00,00,15);
		
		repositorio.consumo_hogar_periodo(desde,hasta);
	}

	@Test
	public void reporte_consumo_promedio_tipo_dispositivo_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,10,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,10,28,20,00,00,15);
		
		repositorio.consumo_promedio_tipo_dispositivo_periodo(desde,hasta);
	}

	@Test
	public void reporte_consumo_transformador_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,10,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,10,28,20,00,00,15);
		
		repositorio.consumo_transformador_periodo(desde,hasta);
	}
	
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}
}
