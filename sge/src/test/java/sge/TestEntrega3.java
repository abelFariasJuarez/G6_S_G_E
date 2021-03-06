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
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.regla.AccionPrender;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;
import sge.modelo.regla.comparador.MayorIgual;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;

public class TestEntrega3 {
	private Repositorio repo;

	@Before
	public void setUp() throws Exception {
		repo = Repositorio.getInstance();
		repo.abrir();
	}

	@Test
	public void casoDePrueba1() {

		// Caso de prueba 1: Crear 1 usuario nuevo.
		this.crearUsuarioDelCasoDePrueba();		

		// Recuperarlo,
		Cliente clienteRecuperado = repo.clientes().findBy("username", "fperez");

		// modificar la geolocalizaci�n
		clienteRecuperado.getUbicacion().setLongitud(130.0);

		// y grabarlo.
		repo.clientes().persistir(clienteRecuperado);

		// Recuperarlo
		Cliente clienteRecuperado2 = repo.clientes().findBy("username", "fperez");

		// y evaluar que el cambio se haya realizado.
		assertEquals(clienteRecuperado2.getUbicacion().getLongitud(), 130.0, 0);
	}

	@Test
	public void casoDePrueba2() {
		// Caso de prueba 2:
		this.crearDispositivoDelCasoDePrueba();

		// Recuperar un dispositivo.
		DispositivoInteligente unDispo = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");

		// Mostrar por consola todos los intervalos que estuvo
		// encendido durante el �ltimo mes.
		unDispo.presentate();
		unDispo.mostrarIntervalosEncendidos();

		// Modificar su nombre (o cualquier otro atributo editable)
		unDispo.setBajoConsumo(false);

		// y grabarlo.
		repo.dispositivos().persistir(unDispo);

		// Recuperarlo
		DispositivoInteligente unDispoRecuperado = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");

		// y evaluar que el nombre coincida con el esperado.
		assertEquals(unDispoRecuperado.getBajoConsumo(), false);
	}

	@Test
	public void casoDePrueba3() {
		
		this.crearDispositivoDelCasoDePrueba();
		DispositivoInteligente unDispo = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		unDispo.apagar();
		
		// Caso de prueba 3:
		// Crear una nueva regla.

		// Asociarla a un dispositivo.
		// Agregar condiciones
		// y acciones.
		Sensor unS = repo.sensores().findBy("nombre", "sensorCasoPrueba3");
		if (unS == null) {
			unS = new Sensor();
		}

		Comparador cmp = repo.comparaciones().findBy("oid", 1L);
		if (cmp == null) {
			cmp = new MayorIgual();
		}

		Condicion cond = repo.condiciones().findBy("oid", 1L);
		if (cond == null) {
			cond = new Condicion();
		}

		Condicion cond2 = repo.condiciones().findBy("oid", 2L);
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
		
		Regla unRegla = repo.reglas().findBy("name", "reglaCasoPrueba3");
		if (unRegla == null) {
			unRegla = new Regla("reglaCasoPrueba3");
			unRegla.agregarCondicion(cond);
			unRegla.agregarCondicion(cond2);
			unRegla.agregarAccion(new AccionPrender());
		}		

		// Persistirla.		
		repo.reglas().persistir(unRegla);

		// Recuperarla
		Regla unReglaRecuperada = repo.reglas().findBy("name", "reglaCasoPrueba3");

		// y ejecutarla.
		assertEquals(unDispo.estoyON(), false);
		unReglaRecuperada.accionarSiCorresponde(unDispo);
		assertEquals(unDispo.estoyON(), true);

		// Modificar alguna condici�n
		cond2.setValorEsperado(20.0);

		// y persistirla.
		repo.condiciones().persistir(cond2);

		// Recuperarla
		Condicion cond2Recuperada = repo.condiciones().findBy("oid", cond2.getOid());

		// y evaluar que la condici�n modificada posea la �ltima modificaci�n.
		assertEquals(cond2Recuperada.getValorEsperado(), 20.0, 0.0);
	}

	@Test
	public void casoDePrueba4() {
		// Caso de prueba 4:
		
		// Agregar una instancia de Transformador al JSON de entradas. ***altere el orden de los pasos del caso de prueba
		// Ejecutar el m�todo de lectura y persistencia. ***altere el orden de los pasos del caso de prueba
		repo.transformadores().cargarTransformadores();
		repo.transformadores().guardarTransforamdores();
		
		// Recuperar todos los transformadores persistidos.
		List<Transformador> ts = repo.transformadores().buscarTodos(); 
		
		// Registrar la cantidad.		
		Integer cantidadT1 = ts.size();
		Integer cantidadT2 = repo.transformadores().getTransformadores().size();
		
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
		
		// Dado un dispositivo y un per�odo, mostrar por consola su
		// consumo promedio.
		this.crearDispositivoDelCasoDePrueba();
		DispositivoInteligente unDispo = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");	

		Double consumoDispositivo = unDispo.consumo_periodo(instanteDesde, instanteHasta);		
		System.out.println("\tconsumo promedio: " + consumoDispositivo + "del dispositivo en el periodo");
		
		// Dado un hogar y un per�odo, mostrar por consola (interfaz de comandos) el
		// consumo total.
		this.crearUsuarioDelCasoDePrueba();		
		Cliente unCliente = repo.clientes().findBy("username", "fperez");	
		unCliente.addDispositivo(unDispo);
		Double consumoCliente =unCliente.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio: " + consumoCliente + "del cliente en el periodo");
		assertEquals(consumoDispositivo, consumoCliente, 0);
		
		// Dado un transformador y un per�odo, mostrar su consumo promedio. 
		repo.transformadores().cargarTransformadores();
		repo.transformadores().guardarTransforamdores();
		Transformador unTrans = repo.transformadores().findBy("oid", 1L);
		unTrans.addCliente(unCliente);		
		Double consumoTrans = unTrans.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio: " + consumoTrans + "del Transformador en el periodo");
		assertEquals(consumoDispositivo, consumoTrans, 0);
		
		// Recuperar un dispositivo asociado a un hogar de ese transformador e
		// incrementar un 1000 % el consumo para ese per�odo. 
		DispositivoInteligente unDispoRecu = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		unDispoRecu.setConsumoPorHora(unDispoRecu.getConsumoPorHora() * 1000);		
		// Persistir el dispositivo.
		repo.dispositivos().persistir(unDispoRecu);
		
		// Nuevamente mostrar el consumo para ese transformador.
		Double consumoTrans1000 = unTrans.consumoEnPeriodo(instanteDesde, instanteHasta);
		System.out.println("\tconsumo promedio * 1000 : " + consumoTrans + "del Transformador en el periodo");
		assertEquals(consumoDispositivo * 1000, consumoTrans1000, 0);
		
		unCliente.removeDispositivo(unDispo);
		repo.clientes().persistir(unCliente);
		
		unTrans.removeCliente(unCliente);
		repo.transformadores().persistir(unTrans);
		
	}

	private void crearDispositivoDelCasoDePrueba() {
		//DISPOSITIVO 1
		DispositivoInteligente unDispo = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		if (unDispo == null) {
			unDispo = new DispositivoInteligente("DispoCasoPrueba", 7.1, true, new DriverBasico());

			unDispo.prender();
			unDispo.apagar();
			unDispo.prender();
			unDispo.apagar();
			unDispo.prender();
			unDispo.apagar();
			
			repo.dispositivos().persistir(unDispo);	
		}

		unDispo = (DispositivoInteligente) repo.dispositivos().findBy("nombre",
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

			unDispo.setBajoConsumo(true);
			unDispo.setConsumoPorHora(7.1);
			repo.dispositivos().persistir(unDispo);
		}
		
	}
	
	private void crearUsuarioDelCasoDePrueba() {
		//Cliente 1
		Cliente clienteOriginal = repo.clientes().findBy("username", "fperez");
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
		
		repo.clientes().persistir(clienteOriginal);
		
	}	

	@Test
	public void reporte_consumo_hogar_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,1,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,12,30,20,00,00,15);
		
		repo.consumo_hogar_periodo(desde,hasta);
	}

	@Test
	public void reporte_consumo_promedio_tipo_dispositivo_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,1,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,12,28,20,00,00,15);
		
		repo.consumo_promedio_tipo_dispositivo_periodo(desde,hasta);
	}

	@Test
	public void reporte_consumo_transformador_periodo() throws Exception {
		LocalDateTime desde = LocalDateTime.of(2018,1,28,01,00,00);
		LocalDateTime hasta = LocalDateTime.of(2018,12,28,20,00,00,15);
		
		repo.consumo_transformador_periodo(desde,hasta);
	}
	
	@Test
	public void cargaTransformadores() {
		
		repo.transformadores().cargarTransformadores();
		repo.transformadores().guardarTransforamdores();
	}
	
	@After
	public void tearDown() throws Exception {
		repo.cerrar();
	}
}
