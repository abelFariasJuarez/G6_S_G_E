package sge;

import static org.junit.Assert.*;


import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Repositorios.RepositorioDeClientes;

public class TestCliente {

	RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
	List<Cliente> Clientes ;
	Cliente cli ;
	
	
	@Before
	public void setUp() {
		repo.cargarClientes();
		cli = repo.clientes.get(0);
		Clientes = repo.clientes;
		
		
	}
	
	/*@Test
	public void mostrarClientesYDispositivos() {
		
		for (Cliente client : Clientes) {
			client.presentate();
			for (Dispositivo disp : client.dispositivos) {
				disp.presentate();
			}

		}
	}*/
	
	@Test
	public void hayClientes(){
		
		assertEquals(true,!Clientes.isEmpty());
	}
	
	@Test
	public void algunDispositivoOn() {
		

		assertEquals(true, cli.tengoAlgunDispositivoON());

	}

	@Test
	public void cantDispositivosOn() {


		assertEquals(Integer.valueOf(3), cli.cantDispositivosON());

	}

	@Test
	public void cantDispositivosOFF() {

		assertEquals(Integer.valueOf(2), cli.cantDispositivosOFF());

	}

	@Test
	public void cantDispositivos() {


		assertEquals(Integer.valueOf(5), cli.cantDispositivos());

	}

}
