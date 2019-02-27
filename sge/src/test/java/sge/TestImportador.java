package sge;

import static org.junit.Assert.*;

import org.junit.Test;

import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Dispositivos;
import sge.repositorios.Transformadores;
import sge.repositorios.Zonas;
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;

public class TestImportador {

	@Test
	public void ImportadorJsonDispositivo() {
		Dispositivos repoDispositivos = Repositorio.getInstance().dispositivos();
		repoDispositivos.cargarDispositivos("todos");

		assertEquals(24, repoDispositivos.getDispositivos().size());

		for (Dispositivo disp : repoDispositivos.getDispositivos()) {
			disp.presentate();
			System.out.println(disp.getClass());
		}
	}

	@Test
	public void ImportadorJsonZona() {
		Zonas repoZonas = Repositorio.getInstance().zonas();
		repoZonas.cargarZonas();

		assertEquals(3, repoZonas.getZonas().size());

		for (ZonaGeografica zona : repoZonas.getZonas()) {

			System.out.println(zona.getNombre());
		}		
	}

	@Test
	public void ImportadorJsonTransformador() {
		Transformadores repoTransfo = Repositorio.getInstance().transformadores();
		repoTransfo.cargarTransformadores();
		assertEquals(10, repoTransfo.getTransformadores().size());

		for (Transformador transfo : repoTransfo.getTransformadores()) {

			System.out.println(transfo.getId());
		}
	}

	@Test
	public void ImportadorJsonCliente() {
		Clientes repoClientes = Repositorio.getInstance().clientes();
		repoClientes.cargarClientesDesdeJson();
		assertEquals(4, repoClientes.getClientesJson().size());

		for (Cliente cli : repoClientes.getClientesJson()) {

			cli.presentate();
		}
	}

}
