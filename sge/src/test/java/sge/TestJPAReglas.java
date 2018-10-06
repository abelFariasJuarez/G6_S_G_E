package sge;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sge.modelo.dispositivo.DispositivoConModulo;
import sge.modelo.dispositivo.DispositivoEstandar;
import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.driver.DriverBasico;
import sge.modelo.regla.AccionPrender;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.Comparador;
import sge.modelo.regla.comparador.Igual;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;
import sge.repositorios.Dispositivos;
import sge.repositorios.RepositorioRestriccionHorasFamilia;

public class TestJPAReglas {

	private Repositorio repositorio;

	@Before
	public void setUp() throws Exception {
		repositorio = new Repositorio();
		repositorio.abrir();
	}

	@Test
	public void aPersistirSensorComparadorCondicion() {
		Sensor unS = null;
		unS = repositorio.sensores().findBy("nombre", "sensor1");
		if (unS == null) {
			unS = new Sensor();
			unS.setMedicion(0.0);
			unS.setNombre("sensor1");
			unS.setTiempoDeEspera(30.0);

			repositorio.persistir(unS);
		}

		Comparador cmp = null;
		cmp = repositorio.comparaciones().findBy("oid", (long) 1);
		if (cmp == null) {
			cmp = new Igual();
			repositorio.persistir(cmp);
		}

		Condicion cond = null;
		cond = repositorio.condiciones().findBy("oid", (long) 1);
		if (cond == null) {
			cond = new Condicion();
			cond.setComparador(cmp);
			cond.setSensor(unS);
			cond.setValorEsperado(34.0);
			repositorio.persistir(cond);
		}

		Regla unRegla = null;
		unRegla = repositorio.reglas().findBy("name", "regla 1");
		if (unRegla == null) {
			unRegla = new Regla("regla 1");
			unRegla.agregarCondicion(cond);

			AccionPrender prenderAire = new AccionPrender();
			unRegla.agregarAccion(prenderAire);

			repositorio.persistir(unRegla);

		}

	}

	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
	}

}
