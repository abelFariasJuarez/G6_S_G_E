package sge;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.*;
import sge.regla.comparador.*;



public class App {

	public static void main(String[] args) throws InterruptedException {

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

	}
	}



