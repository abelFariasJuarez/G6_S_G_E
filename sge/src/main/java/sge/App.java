package sge;


import java.time.LocalDate;




public class App {


	public static void main(String[] args) {
		

		
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


		
	


}
	}



