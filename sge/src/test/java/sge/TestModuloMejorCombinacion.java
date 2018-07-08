package sge;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sge.dispositivo.DispositivoEstandar;
import sge.hogareficiente.Recomendacion;

public class TestModuloMejorCombinacion {
	@Test
	public void testSistemaCompatibleDeterminado() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

		DispositivoEstandar estandar0 = new DispositivoEstandar("Lavarropas", 0.18, "perez", true, 12.0);
		DispositivoEstandar estandar1 = new DispositivoEstandar("Computadora", 0.875, "perez", true, 12.0);
		DispositivoEstandar estandar2 = new DispositivoEstandar("Computadora", 0.06, "perez", true, 12.5);

		estandar0.mensualMaximoHoras(370.0);
		estandar0.mensualMinimoHoras(90.0);

		estandar1.mensualMaximoHoras(30.0);
		estandar1.mensualMinimoHoras(6.0);

		estandar2.mensualMaximoHoras(360.0);
		estandar2.mensualMinimoHoras(120.0);

		unCliente.addDispositivo(estandar0);
		unCliente.addDispositivo(estandar1);
		unCliente.addDispositivo(estandar2);

		List<Recomendacion> recomendaciones = unCliente.getMejorCombinacionDispositivos();

		recomendaciones.forEach(r -> {
			if (r.nodo().equals(estandar0)) {
				Assert.assertEquals(370, r.horas(), 0.01);
			}

			if (r.nodo().equals(estandar1)) {
				Assert.assertEquals(30, r.horas(), 0.01);
			}

			if (r.nodo().equals(estandar2)) {
				Assert.assertEquals(360, r.horas(), 0.01);
			}

		});

	}

	@Test
	public void canYouGetMejorCombinacionDispositivosFalse() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

		DispositivoEstandar estandar0 = new DispositivoEstandar("Lavarropas", 1800.0, "perez", true, 12.0);
		DispositivoEstandar estandar1 = new DispositivoEstandar("Computadora", 0.875, "perez", true, 12.0);
		DispositivoEstandar estandar2 = new DispositivoEstandar("Computadora", 0.06, "perez", true, 12.5);

		estandar0.mensualMaximoHoras(370.0);
		estandar0.mensualMinimoHoras(365.0);

		estandar1.mensualMaximoHoras(30.0);
		estandar1.mensualMinimoHoras(6.0);

		estandar2.mensualMaximoHoras(360.0);
		estandar2.mensualMinimoHoras(120.0);

		unCliente.addDispositivo(estandar0);
		unCliente.addDispositivo(estandar1);
		unCliente.addDispositivo(estandar2);

		Assert.assertFalse(unCliente.canYouGetMejorCombinacionDispositivos());

	}
}
