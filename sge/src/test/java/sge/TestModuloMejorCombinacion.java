package sge;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import sge.dispositivo.DispositivoEstandar;

public class TestModuloMejorCombinacion {
	@Test
	public void testSistemaCompatibleDeterminado() {
		Cliente unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana",
				"menToL2017", "Dni", 21321012, 1543312310);
		
		DispositivoEstandar estandar = new DispositivoEstandar("Lavarropas", 2.0,"perez",true,12.5);
		DispositivoEstandar estandar2 = new DispositivoEstandar("Computadora", 2.0,"perez",true,12.5);
		
		estandar.mensualMaximoHoras(30.0);
		estandar.mensualMinimoHoras(6.0);
		
		estandar2.mensualMaximoHoras(440640.0);
		estandar2.mensualMinimoHoras(60.0);
		
		unCliente.addDispositivo(estandar);
		unCliente.addDispositivo(estandar2);
		
		unCliente.getMejorCombinacionDispositivos();
	}
}
