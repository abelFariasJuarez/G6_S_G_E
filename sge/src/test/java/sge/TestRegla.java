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
		
		//Regla unRegla = new Regla("regla 1");
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3,"perez", false);
		// el constructor ya me da un dispo en estado apagado
		//assertEquals(true,unAire.estoyOFF());
		//Regla unRegla = new Regla("regla 1");
		
		Sensor temperatura = new Sensor(30.0, 10.0);
		Sensor humedad = new Sensor(100.0, 50.0);
		humedad.agregarObserver(unRegla);
		temperatura.agregarObserver(unRegla);
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);
		
		Actuador prenderAire = new ActuadorPrender(unAire);
		unRegla.agregarActuador(prenderAire);
		
		
		temperatura.actualizarMedicion(40);
		
		assertEquals(true,unAire.estoyON());
		
	}
	@Test
public void noCumpleCondicionesNoApagar() {
		
		
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.8,"perez",true);
		
		
		Sensor temperatura = new Sensor(18, 10.0);
		temperatura.agregarObserver(unRegla);
		
		Condicion condTemp = new Condicion(temperatura, new Igual(), 20.0);
	
		unRegla.agregarCondicion(condTemp);
		
		
		Actuador ApagarAire = new ActuadorApagar(unAire);
		unRegla.agregarActuador(ApagarAire );
		
		temperatura.actualizarMedicion(19);
		
		assertEquals(false,unAire.estoyOFF());
		
	}
	

}
