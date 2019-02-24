package sge;

import static org.junit.Assert.*;

import org.junit.Test;

import sge.modelo.Repositorio;
import sge.modelo.Zonas;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.ZonaVO;

public class TestImportador {

	@Test
	public void ImportadorJsonDispositivo() {
		Dispositivos repoDispositivos = new Repositorio().dispositivos();
		repoDispositivos.cargarDispositivos("todos");

		assertEquals(24, repoDispositivos.getDispositivos().size());

		for (DispositivoVO disp : repoDispositivos.getDispositivos()) {
			disp.presentate();
			System.out.println(disp.getClass());
		}
	}

	@Test
	public void ImportadorJsonZona() {
		Zonas repoZonas = new Zonas();
		repoZonas.cargarZonas();

		assertEquals(3, repoZonas.getZonas().size());

		for (ZonaVO zona : repoZonas.getZonas()) {

			System.out.println(zona.getNombre());
		}		
	}

	@Test
	public void ImportadorJsonTransformador() {
		Transformadores repoTransfo = new Repositorio().transformadores();
		repoTransfo.cargarTransformadores();
		assertEquals(10, repoTransfo.getTransformadores().size());

		for (TransformadorVO transfo : repoTransfo.getTransformadores()) {

			System.out.println(transfo.getId());
		}
	}

	@Test
	public void ImportadorJsonCliente() {
		Clientes repoClientes = (new Repositorio()).clientes();
		repoClientes.cargarClientesDesdeJson();
		assertEquals(4, repoClientes.getClientesJson().size());

		for (ClienteVO cli : repoClientes.getClientesJson()) {

			cli.presentate();
		}
	}

}
