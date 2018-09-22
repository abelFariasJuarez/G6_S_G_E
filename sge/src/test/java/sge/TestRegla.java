package sge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.driver.AccionApagar;
import sge.modelo.driver.AccionPrender;
import sge.modelo.driver.DriverBasico;
import sge.modelo.driver.RegistroReglas;
import sge.modelo.driver.RegistroSensores;
import sge.modelo.regla.*;
import sge.modelo.regla.comparador.*;

public class TestRegla {

	Regla unRegla = new Regla("regla 1");

	@Test
	public void cumpleCondicionesPrender() {

		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());

		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3, "perez", false,false,driver);
		// el constructor ya me da un dispo en estado apagado

		Sensor temperatura = new Sensor(40.0, 10000.0, "temperatura");
		Sensor humedad = new Sensor(100.0, 5000.0, "humedad");
		
		unAire.agregarSensor(temperatura);
		unAire.agregarSensor(humedad);
		
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);

		AccionPrender prenderAire = new AccionPrender();
		unRegla.agregarAccion(prenderAire);
		
		List <Regla> reglas=new ArrayList<Regla>();
		reglas.add(unRegla);
		
		RegistroSensores.getInstance().registrarSensor(humedad, unAire);
		RegistroSensores.getInstance().registrarSensor(temperatura, unAire);
		RegistroReglas.getInstance().registrarReglas(unAire,reglas );
		
		
		temperatura.actualizarMedicion();
		
		assertEquals(true, unAire.estoyON());

	}

	@Test
	public void noCumpleCondicionesNoApagar() {
		
		DriverBasico driver=new DriverBasico(new ActuadorApagar(), new ActuadorPrender(), new ActuadorAhorro());
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.8, "perez",false, true,driver);

		Sensor temperatura2 = new Sensor(18, 2000.0, "temperatura2");
		Condicion condTemp = new Condicion(temperatura2, new Igual(), 20.0);

		unRegla.agregarCondicion(condTemp);

		AccionApagar ApagarAire = new AccionApagar();
		unRegla.agregarAccion(ApagarAire);

		RegistroSensores.getInstance().registrarSensor(temperatura2, unAire);
		List <Regla> reglas=new ArrayList<Regla>();
		reglas.add(unRegla);
		RegistroReglas.getInstance().registrarReglas(unAire, reglas);
		temperatura2.setMedicion(10);
		

		assertEquals(false, unAire.estoyOFF());

	}

}
