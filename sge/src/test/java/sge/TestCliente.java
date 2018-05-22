package sge;

import static org.junit.Assert.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.*;


public class TestCliente {

	static RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
	static List<Cliente> clientes;
	static Cliente cli;
	static Categoria unaCategoria;
	static RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	static List<Dispositivo> dispositivos;
	static Cliente cli2;
	

	@BeforeClass
	
	public static void setUp()  {
		repo2.cargarDispositivos();
		dispositivos = repo2.Dispositivos();
		repo.cargarClientes();

		clientes = repo.clientes;
		unaCategoria = new Categoria("r1", 18.76f, 0.644f, 0f, 150.0f);
	
		cli=clientes.get(0);
		cli2=clientes.get(1);
		for (Cliente cli : repo.clientes) {
			for (Dispositivo dis : repo2.Dispositivos) {
				if (cli.getUsername().equals(dis.getIdUserName())) {
					cli.addDispositivo(dis);
				}

			}
		
		}
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
		assertEquals(Integer.valueOf(1), cli.cantDispositivosON());
	}

	@Test
	public void cantDispositivosOFF() {
		assertEquals(Integer.valueOf(1), cli.cantDispositivosOFF());
	}

	@Test
	public void cantDispositivos() {
		assertEquals(Integer.valueOf(3), cli.cantDispositivos());
	}

	@Test
	public void cantidadConsumo() {
		assertEquals(61.0, cli.consumo(),0);
	}

	@Test
	public void estimacionFactura() {
		LocalDateTime desde = LocalDateTime.parse("2018-05-20T20:00:00.000000000");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-20T21:00:00.000000000");
		
		assertEquals(19.83, unaCategoria.CostoEstimado(cli2,desde,hasta),0);
	}

	@Test
	public void conversion_a_inteligente_diez_puntos() {
		DispositivoEstandar comun = new DispositivoEstandar("microondas", 12.0, "pepe",10.0);
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
		Dispositivo comun = new DispositivoEstandar("microondas", 12.0, "pepe",10.0);
		unCliente.addDispositivo(comun);
		DispositivoConModulo conModulo = unCliente.agrega_modulo_a_estandar((DispositivoEstandar) comun);

		assertEquals(true, conModulo.estoyOFF());
	}

	@Test
	public void consultarConsumoPeriodoDelClienteConvirtiendoEstandarAModulo() {
		Cliente clien = new Cliente("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0, "pepe",11.0);
		clien.addDispositivo(disestandar);
		disestandar.setHorasEncendido(9.0);
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
		dismodulo.intervalos.get(0).setInicio(desde_apagado);
		dismodulo.intervalos.get(0).setFin(hasta_apagado);
		dismodulo.intervalos.get(1).setInicio(desde_prendido);
		dismodulo.intervalos.get(1).setFin(hasta_prendido);
		// Desde-Hasta donde quiero el periodo
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T20:30:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		assertEquals(1044.0, clien.consumoEnPeriodo(desde, hasta), 0);

	}
}
