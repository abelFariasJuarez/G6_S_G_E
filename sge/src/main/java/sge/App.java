package sge;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.*;
import sge.regla.Actuador;
import sge.regla.ActuadorPrender;
import sge.regla.Condicion;
import sge.regla.Regla;
import sge.regla.Sensor;
import sge.regla.comparador.*;



public class App {

	public static void main(String[] args) throws InterruptedException {



	Regla unRegla = new Regla("regla 1");

		
		//Regla unRegla = new Regla("regla 1");
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3,"perez", false);
		// el constructor ya me da un dispo en estado apagado
		//assertEquals(true,unAire.estoyOFF());
		//Regla unRegla = new Regla("regla 1");
		
		Sensor temperatura = new Sensor(30.0, 10000,"temperatura");
		Sensor humedad = new Sensor(100.0, 5000,"humedad");
		humedad.agregarObserver(unRegla);
		temperatura.agregarObserver(unRegla);
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);
		
		Actuador prenderAire = new ActuadorPrender(unAire);
		unRegla.agregarActuador(prenderAire);
		
		humedad.activar();
		temperatura.setMedicion(40);
		temperatura.activar();
		//temperatura.actualizarMedicion();
		
		//assertEquals(true,unAire.estoyON());
		
	
	}
	
}


