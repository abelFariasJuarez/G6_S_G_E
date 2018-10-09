package sge;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
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
		Cliente clienteOriginal = repositorio.clientes().findBy("username", "fperez");
		if (clienteOriginal == null) {
			clienteOriginal = new Cliente("Felipe", "Perez", "Medrano 123", LocalDate.of(1995, 4, 7), "fperez",
					"menToL2017", "Dni", 11222333, 1543312311);
			clienteOriginal.setAhorroAutomatico(false);
			clienteOriginal.setUbicacion(new Ubicacion(10.0, 90.0));
		}
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
		// Caso de prueba 3:
		// Crear una nueva regla.
		Regla unRegla = repositorio.reglas().findBy("name", "reglaCasoPrueba3");
		if (unRegla == null) {
			unRegla = new Regla("reglaCasoPrueba3");
		}

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

		unRegla.agregarAccion(new AccionPrender());

		unS.setMedicion(40.0);
		unS.setNombre("sensorCasoPrueba3");
		unS.setTiempoDeEspera(60.0);

		cond.setComparador(cmp);
		cond.setSensor(unS);
		cond.setValorEsperado(34.0);

		cond2.setComparador(cmp);
		cond2.setSensor(unS);
		cond2.setValorEsperado(36.0);

		this.crearDispositivoDelCasoDePrueba();
		DispositivoInteligente unDispo = (DispositivoInteligente) repositorio.dispositivos().findBy("nombre",
				"DispoCasoPrueba");
		unDispo.apagar();

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
		} else {
			unDispo.setBajoconsumo(true);
		}

		repositorio.persistir(unDispo);

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
		// Evaluar que la cantidad de tranformadores en la BD sea igual
		// a la cantidad de tranformadores leidos del json ***re entirpretado
		assertEquals(cantidadT1, cantidadT2, 0);
	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}
}
