package sge;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import sge.dispositivo.*;
import sge.regla.comparador.*;



public class App {

	public static void main(String[] args) throws InterruptedException {

		Cliente cli = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);

		Dispositivo diselectronico = new DispositivoInteligente("heladera", 15.0, false);
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0);

		System.out.println("pepe");
		cli.addDispositivo(disestandar);
		cli.addDispositivo(diselectronico);
		cli.addDispositivo(diselectronico);

		System.out.println(cli.cantDispositivosOFF());
		System.out.println(cli.cantDispositivosON());
		
		Comparador c = new Mayor();
		System.out.println(c.comparar(1.1,0.1));
		
		
		
	
	
	

	}
}
