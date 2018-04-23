package sge;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestCliente {

	
	Cliente cli;
	
	@Before
	public void setUp() {
		
		cli = new Cliente("DNI", 38522013, 1144320119, new ArrayList<Dispositivo>());
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("microondas", 15, false));
		cli.addDispositivo(new Dispositivo("monitor", 12, false));
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("Estufa Electrica", 12, true));
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
