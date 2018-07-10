package sge;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sge.dispositivo.familia.*;
import sge.hogareficiente.Recomendacion;

public class TestModuloMejorCombinacion {
	@Test
	public void testSistemaCompatibleDeterminado() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

		AireAcondicionadoInteligente air = new AireAcondicionadoInteligente("Lavarropas", 0.18, true);
		Lavarropas lava = new Lavarropas("Computadora", 0.875,true);
		Ventilador unVenti = new Ventilador("Ventilador", 0.06, true);

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
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);

	
		AireAcondicionadoInteligente air = new AireAcondicionadoInteligente("Lavarropas", 1000.0, true);
		ComputadoraInteligente pc = new ComputadoraInteligente("Computadora", 2000.0,true);
		Ventilador unVenti = new Ventilador("Ventilador", 3000.0, true);

		unCliente.addDispositivo(air);
		unCliente.addDispositivo(pc);
		unCliente.addDispositivo(unVenti);

		Assert.assertFalse(unCliente.canYouGetMejorCombinacionDispositivos());

	}
}
