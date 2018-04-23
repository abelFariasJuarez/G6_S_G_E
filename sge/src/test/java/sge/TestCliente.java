package sge;

import static org.junit.Assert.*;

import java.util.ArrayList;



import org.junit.Test;

public class TestCliente {

	@Test
	public void algunDispositivoOn() {
		Cliente cli = new Cliente("DNI", 38522013, 1144320119, new ArrayList<Dispositivo>());
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("microondas", 15, false));
		cli.addDispositivo(new Dispositivo("monitor", 12, false));

		assertEquals(true, cli.tengoAlgunDispositivoON());

	}

	@Test
	public void cantDispositivosOn() {
		Cliente cli = new Cliente("DNI", 38522013, 1144320119, new ArrayList<Dispositivo>());
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("microondas", 15, false));
		cli.addDispositivo(new Dispositivo("monitor", 12, false));
		cli.addDispositivo(new Dispositivo("heladera", 12, true));

		assertEquals(Integer.valueOf(2), cli.cantDispositivosON());

	}

	@Test
	public void cantDispositivosOFF() {
		Cliente cli = new Cliente("DNI", 38522013, 1144320119, new ArrayList<Dispositivo>());
		cli.addDispositivo(new Dispositivo("heladera", 12, false));
		cli.addDispositivo(new Dispositivo("microondas", 15, false));
		cli.addDispositivo(new Dispositivo("monitor", 12, false));
		cli.addDispositivo(new Dispositivo("heladera", 12, false));

		assertEquals(Integer.valueOf(4), cli.cantDispositivosOFF());

	}

	@Test
	public void cantDispositivos() {

		Cliente cli = new Cliente("DNI", 38522013, 1144320119, new ArrayList<Dispositivo>());
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("microondas", 15, false));
		cli.addDispositivo(new Dispositivo("monitor", 12, false));
		cli.addDispositivo(new Dispositivo("heladera", 12, true));
		cli.addDispositivo(new Dispositivo("Estufa Electrica", 12, true));

		assertEquals(Integer.valueOf(5), cli.cantDispositivos());

	}

}
