package sge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sge.dispositivo.DispositivoInteligente;
import sge.regla.*;
import sge.regla.comparador.*;

public class TestRegla {
	
	@Test
	public void cumpleCondicionesPrender() {
		
		
		DispositivoInteligente unAire = new DispositivoInteligente("AireCondicionado", 2.3, false);
		// el constructor ya me da un dispo en estado apagado
		//assertEquals(true,unAire.estoyOFF());
		Regla unRegla = new Regla("regla 1");
		
		Sensor temperatura = new Sensor(34.0, 10.0);
		Sensor humedad = new Sensor(100.0, 50.0);
		
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondision(condTemp);
		unRegla.agregarCondision(condHume);
		
		Actuador prenderAire = new ActuadorPrender(unAire);
		unRegla.agregarActuador(prenderAire);
		
		unRegla.accionarSiCorresponde();

		assertEquals(true,unAire.estoyON());
		
	}
	

}
