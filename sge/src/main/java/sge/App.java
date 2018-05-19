package sge;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;




public class App {


	public static void main(String[] args) throws InterruptedException {
		

		
		Cliente cli = new Cliente("Pedro","Ramon","Plaza",LocalDate.of(1989, 11, 11),"pedro","nikita","dni",31032123,115322011);

		Dispositivo diselectronico=new DispositivoInteligente("heladera",15f,true);
		Dispositivo disestandar=new DispositivoEstandarConcreto("microondas",12f);
		Dispositivo moulo=new Modulo((DispositivoEstandar) disestandar,false);
	
		
	
	
		System.out.println("pepe");
		cli.addDispositivo(disestandar);
		cli.addDispositivo(diselectronico);
		cli.addDispositivo(diselectronico);
		cli.addDispositivo(moulo);
		
		System.out.println( cli.cantDispositivosOFF());
		System.out.println(cli.cantDispositivosON());

		
	/*
		  Sensor publisher = new Sensor(4, 5);
		  
	      // Create a subscriber and register it with the publisher.

	      Condicion<Integer> subscriber = new Condicion<>();
	      Condicion<Integer> subscriber2 = new Condicion<>();
	     
	      publisher.subscribe(subscriber);
	      publisher.subscribe(subscriber2);
	     
	      Sensor t=new Sensor(4,5);
	      Sensor x=new Sensor(2,5);
	      Sensor r=new Sensor(3,5);

	      
	      

	      // Publish several data items and then close the publisher.

	      System.out.println("Publishing data items...");
	      Integer[] items = {t.v,x.v,r.v};
	      Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
	    
	      
	      publisher.close();
	 


	      try
	      {
	         synchronized("A")
	         {
	            "A".wait();
	         }
	      }
	      catch (InterruptedException ie)
	      {
	   
	      */
		
		
		Sensor publisher = new Sensor(4);

		Condicion subscriberA = new Condicion("A");
		Condicion subscriberB = new Condicion("B");
		
		publisher.subscribe(subscriberA);
		publisher.subscribe(subscriberB);
		publisher.setV(2);
		publisher.waitUntilTerminated();
	}
}
	   


	







