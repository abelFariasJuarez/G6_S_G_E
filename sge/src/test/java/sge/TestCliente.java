package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sge.modelo.ActuadorAhorro;
import sge.modelo.ActuadorApagar;
import sge.modelo.ActuadorPrender;
import sge.modelo.DriverBasico;
import sge.modelo.Repositorio;
import sge.modelo.dispositivo.*;
import sge.modelo.valueobjects.CategoriaVO;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoConModuloVO;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;

public class TestCliente {

	static Repositorio repo1 = new Repositorio();
	static Clientes repoClientes = repo1.clientes();
	static List<ClienteVO> clienteVOs;
	static ClienteVO cli, cli2;
	static CategoriaVO unaCategoria;
	static Dispositivos repoDispositivos = repo1.dispositivos();
	static List<DispositivoVO> dispositivos;

	static ClienteVO unCliente;
	static List<DispositivoVO> dispos = new ArrayList<DispositivoVO>();
	static DispositivoInteligenteVO dispo1;
	static DispositivoInteligenteVO dispo2;

	@BeforeClass

	public static void setUp() {
		repoDispositivos.cargarDispositivos("todos");
		dispositivos = repoDispositivos.getDispositivos();
		repoClientes.cargarClientesDesdeJson();

		clienteVOs = repoClientes.getClientesJson();
		unaCategoria = new CategoriaVO("r1", 18.76f, 0.644f, 0f, 150.0f);

		// ------------------------------------------------------------
		cli = clienteVOs.get(0);
		DispositivoInteligenteVO pc = new DispositivoInteligenteVO("televisor", 5.0, "cazana", false, true,
				new DriverBasico());
		cli.addDispositivo(pc);
		// ------------------------------------------------------------

		cli2 = clienteVOs.get(1);
		for (ClienteVO cli : repoClientes.getClientesJson()) {
			for (DispositivoVO dis : repoDispositivos.Dispositivos) {
				if (cli.getUsername().equals(dis.getIdUserName())) {
					cli.addDispositivo(dis);
				}

			}

		}

		clienteVOs = repoClientes.getClientesJson();

		LocalDateTime desde = LocalDateTime.parse("2018-05-19T20:00:00.000000000");
		LocalDateTime hasta = LocalDateTime.parse("2018-05-19T22:00:00.000000000");

		dispo1 = new DispositivoInteligenteVO("heladera", 10.0, "cazana", false, true, new DriverBasico());
		dispo2 = new DispositivoInteligenteVO("televisor", 5.0, "cazana", false, true, new DriverBasico());

		dispo1.apagar();
		dispo2.apagar();

		dispo1.getIntervalos().get(0).setInicio(desde);
		dispo1.getIntervalos().get(0).setFin(hasta);
		dispo2.getIntervalos().get(0).setInicio(desde);
		dispo2.getIntervalos().get(0).setFin(hasta);

		dispos.add(dispo1);
		dispos.add(dispo2);

		unCliente = new ClienteVO("Carlos", "Sanazki", "condarco 148", LocalDate.of(2017, 4, 7), "cazana", "menToL2017",
				"Dni", 21321012, 1543312310);
		unCliente.setDispositivos(dispos);

	}

	@Test
	public void hayClientes() {
		assertEquals(true, !clienteVOs.isEmpty());
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
		DispositivoEstandarVO comun = new DispositivoEstandarVO("microondas", 12.0, "pepe", false, 10.0);
		ClienteVO unCliente = new ClienteVO("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		unCliente.addDispositivo(comun);
		unCliente.agrega_modulo_a_estandar(comun);

		assertEquals(10, unCliente.getPuntos(), 0);
	}

	@Test
	public void conversion_a_inteligente_con_modulo_apagado() {

		ClienteVO unCliente = new ClienteVO("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		DispositivoVO comun = new DispositivoEstandarVO("microondas", 12.0, "pepe", false, 10.0);
		unCliente.addDispositivo(comun);
		DispositivoConModuloVO conModulo = unCliente.agrega_modulo_a_estandar((DispositivoEstandarVO) comun);

		assertEquals(true, conModulo.estoyOFF());
	}

	@Test
	public void consultarConsumoPeriodoDelClienteConvirtiendoEstandarAModulo() {
		ClienteVO clien = new ClienteVO("Pedro", "Ramon", "Plaza", LocalDate.of(1989, 11, 11), "pedro", "nikita", "dni",
				31032123, 115322011);
		DispositivoEstandarVO disestandar = new DispositivoEstandarVO("microondas", 12.0, "pepe", false, 11.0);
		clien.addDispositivo(disestandar);
		disestandar.setHorasEncendidoPorDia(9.0);
		// convierto a dispositivo modulo
		DispositivoConModuloVO dismodulo = clien.agrega_modulo_a_estandar(disestandar);
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
