package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import utils.GestorJson;
import sge.repositorios.Dispositivos;
import sge.modelo.dispositivo.*;
import sge.modelo.driver.ActuadorAhorro;
import sge.modelo.driver.ActuadorApagar;
import sge.modelo.driver.ActuadorPrender;
import sge.modelo.driver.DriverBasico;
import sge.modelo.usuarios.Categoria;
import sge.modelo.usuarios.Cliente;

public class TestCliente {

	static Repositorio repo1 = Repositorio.getInstance();
	static Clientes repoClientes = repo1.clientes();
	static List<Cliente> clientes;
	static Cliente cli, cli2;
	static Categoria unaCategoria;
	static Dispositivos repoDispositivos = repo1.dispositivos();
	static List<Dispositivo> dispositivos;

	static Cliente unCliente;
	static List<Dispositivo> dispos = new ArrayList<Dispositivo>();
	static DispositivoInteligente dispo1;
	static DispositivoInteligente dispo2;

	@BeforeClass

	public static void setUp() {
		repoDispositivos.cargarDispositivos("todos");
		dispositivos = repoDispositivos.getDispositivos();
		repoClientes.cargarClientesDesdeJson();

		clientes = repoClientes.getClientesJson();
		unaCategoria = new Categoria("r1", 18.76f, 0.644f, 0f, 150.0f);

		// ------------------------------------------------------------
		cli = clientes.get(0);
		DispositivoInteligente pc = new DispositivoInteligente("televisor", 5.0, "cazana", false, true,
				new DriverBasico());
		cli.addDispositivo(pc);
		// ------------------------------------------------------------

		cli2 = clientes.get(1);
		for (Cliente cli : repoClientes.getClientesJson()) {
			for (Dispositivo dis : repoDispositivos.Dispositivos) {
				if (cli.getUsername().equals(dis.getIdUserName())) {
					cli.addDispositivo(dis);
				}

			}

		}

		clientes = repoClientes.getClientesJson();

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.000000000");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T22:00:00.000000000");

		dispo1 = new DispositivoInteligente("heladera", 10.0, "cazana", false, true, new DriverBasico());
		dispo2 = new DispositivoInteligente("televisor", 5.0, "cazana", false, true, new DriverBasico());

		dispo1.apagar();
		dispo2.apagar();

		dispo1.getIntervalos().get(0).setInicio(desde);
		dispo1.getIntervalos().get(0).setFin(hasta);
		dispo2.getIntervalos().get(0).setInicio(desde);
		dispo2.getIntervalos().get(0).setFin(hasta);

		dispos.add(dispo1);
		dispos.add(dispo2);

		unCliente = new Cliente("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana", "menToL2017",
				"Dni", 21321012, 1543312310);
		unCliente.setDispositivos(dispos);

	}

	@Test
	public void hayClientes() {
		assertEquals(true, !clientes.isEmpty());
	}

	@Test
	public void algunDispositivoOn() {
		assertEquals(true, cli.tengoAlgunDispositivoON());
	}

	@Test
	public void cantDispositivosOn() {
		assertEquals(Integer.valueOf(0), unCliente.cantDispositivosON());
	}

	@Test
	public void cantDispositivosOFF() {
		assertEquals(Integer.valueOf(2), unCliente.cantDispositivosOFF());
	}

	@Test
	public void cantDispositivos() {
		assertEquals(Integer.valueOf(2), unCliente.cantDispositivos());
	}

	@Test
	public void cantidadConsumoEnPeriodo() {
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T22:00:00.000000000");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-20T22:00:00.000000000");
		assertEquals(30.0, unCliente.consumoEnPeriodo(desde, hasta), 1);
	}

	@Test
	public void estimacionFactura() {
		LocalDateTime desde = LocalDateTime.parse("2018-05-19T18:00:00.000000000");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T22:00:00.000000000");

		assertEquals(38.08, unaCategoria.CostoEstimado(unCliente, desde, hasta), 1);
	}

	@Test
	public void conversion_a_inteligente_diez_puntos() {
		DispositivoEstandar comun = new DispositivoEstandar("microondas", 12.0, "pepe", false, 10.0);
		Cliente unCliente = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		unCliente.addDispositivo(comun);
		unCliente.agrega_modulo_a_estandar(comun);

		assertEquals(10, unCliente.getPuntos(), 0);
	}

	@Test
	public void conversion_a_inteligente_con_modulo_apagado() {

		Cliente unCliente = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		Dispositivo comun = new DispositivoEstandar("microondas", 12.0, "pepe", false, 10.0);
		unCliente.addDispositivo(comun);
		DispositivoConModulo conModulo = unCliente.agrega_modulo_a_estandar((DispositivoEstandar) comun);

		assertEquals(true, conModulo.estoyOFF());
	}

	@Test
	public void consultarConsumoPeriodoDelClienteConvirtiendoEstandarAModulo() {
		Cliente clien = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0, "pepe", false, 11.0);
		clien.addDispositivo(disestandar);
		disestandar.setHorasEncendidoPorDia(9.0);
		// convierto a dispositivo modulo
		DispositivoConModulo dismodulo = clien.agrega_modulo_a_estandar(disestandar);
		dismodulo.setInstanteDeCreacion(LocalDateTime.parse("2018-05-25T20:30:00.775887700"));

		dismodulo.prender();
		// creo los intervalos de tiempo
		LocalDateTime desde_apagado = LocalDateTime.parse("2018-05-25T20:00:00.775887700");
		LocalDateTime hasta_apagado = LocalDateTime.parse("2018-06-17T20:30:00.775887700");
		LocalDateTime desde_prendido = LocalDateTime.parse("2018-06-17T20:30:00.775887700");
		LocalDateTime hasta_prendido = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		// le seteo cuando empiezan y cuando terminan
		dismodulo.getIntervalos().get(0).setInicio(desde_apagado);
		dismodulo.getIntervalos().get(0).setFin(hasta_apagado);
		dismodulo.getIntervalos().get(1).setInicio(desde_prendido);
		dismodulo.getIntervalos().get(1).setFin(hasta_prendido);
		// Desde-Hasta donde quiero el periodo
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T20:30:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		assertEquals(1044.0, clien.consumoEnPeriodo(desde, hasta), 0);

	}
}
