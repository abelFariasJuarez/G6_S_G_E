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

/*	public static void main(String[] args) throws InterruptedException {

		Cliente cli = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);

		Dispositivo diselectronico = new DispositivoInteligente("heladera", 15.0,"perez", false);
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0,"perez",10.1);

		System.out.println("pepe");
		cli.addDispositivo(disestandar);
		cli.addDispositivo(diselectronico);
		cli.addDispositivo(diselectronico);

		System.out.println(cli.cantDispositivosOFF());
		System.out.println(cli.cantDispositivosON());
		
		Comparador c = new Mayor();
		System.out.println(c.comparar(1.1,0.1));
*/
		
/* cargador cliente con dispositivo
		RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
		List<Cliente> clientes;
		
		Categoria unaCategoria;
		RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
		List<Dispositivo> dispositivos;

		
			repo2.cargarDispositivos();
			dispositivos = repo2.Dispositivos();
			repo.cargarClientes();
			
			clientes = repo.clientes;
			unaCategoria = new Categoria("r1", 18.76f, 0.644f, 0f, 150.0f);
			for(Cliente clie:clientes) {
				for(Dispositivo dis:dispositivos) {
				if(clie.getUsername().equals(dis.getIdUserName())){
					clie.addDispositivo(dis);
				}
				
				}
				clie.presentate();
		}
	
		
*/
	public static void main(String[] args) throws InterruptedException{
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
		
		humedad.activarSensor();
		temperatura.setMedicion(40);
		temperatura.activarSensor();
		//temperatura.actualizarMedicion();
		
		//assertEquals(true,unAire.estoyON());
		
	
	}
	
}


