package sge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sge.dispositivo.DispositivoInteligente;
import sge.regla.*;
import sge.regla.comparador.*;

public class TestRegla {

	Regla unRegla = new Regla("regla 1");

	@Test
	public void cumpleCondicionesPrender() {

		
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3, "perez", false,false);
		// el constructor ya me da un dispo en estado apagado
		

		Sensor temperatura = new Sensor(40.0, 10000.0, "temperatura");
		Sensor humedad = new Sensor(100.0, 5000.0, "humedad");
		humedad.agregarObserver(unRegla);
		temperatura.agregarObserver(unRegla);
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);

		Actuador prenderAire = new ActuadorPrender(unAire);
		unRegla.agregarActuador(prenderAire);

	
		temperatura.actualizarMedicion();

		assertEquals(true, unAire.estoyON());

	}

	@Test
	public void noCumpleCondicionesNoApagar() {

		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.8, "perez",false, true);

		Sensor temperatura2 = new Sensor(18, 2000.0, "temperatura2");
		temperatura2.agregarObserver(unRegla);

		Condicion condTemp = new Condicion(temperatura2, new Igual(), 20.0);

		unRegla.agregarCondicion(condTemp);

		Actuador ApagarAire = new ActuadorApagar(unAire);
		unRegla.agregarActuador(ApagarAire);

		temperatura2.setMedicion(10);
		

		assertEquals(false, unAire.estoyOFF());

	}

}
