package sge;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Repositorios.RepositorioDeClientes;

public class TestCliente {

	RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
	List<Cliente> Clientes;
	Cliente cli;
	Categoria unaCategoria;

	@Before
	public void setUp() {
		repo.cargarClientes();
		cli = repo.clientes.get(0);
		Clientes = repo.clientes;
		unaCategoria = new Categoria("r1", 18.76f, 0.644f, 0f, 150.0f);
	}

	@Test
	public void hayClientes() {

		assertEquals(true, !Clientes.isEmpty());
	}

	@Test
	public void algunDispositivoOn() {

		assertEquals(true, cli.tengoAlgunDispositivoON());

	}

	@Test
	public void cantDispositivosOn() {

		assertEquals(Integer.valueOf(2), cli.cantDispositivosON());

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
		assertEquals(Float.valueOf(50), cli.consumo());
	}

	@Test
	public void estimacionFactura() {
		assertEquals(Float.valueOf(50.96f), unaCategoria.CostoEstimado(cli));
	}
}
