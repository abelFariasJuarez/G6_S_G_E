package sge;

import static org.junit.Assert.*;

import org.junit.Test;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeDispositivos;
import Repositorios.RepositorioDeTransformadores;
import Repositorios.RepositorioDeZonas;
import sge.dispositivo.Dispositivo;
import sge.posicionamiento.Transformador;
import sge.posicionamiento.ZonaGeografica;

public class TestImportador {

	@Test
	public void ImportadorJsonDispositivo() {
		RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
		repo2.cargarDispositivos("todos");

		assertEquals(24, repo2.Dispositivos().size());

		for (Dispositivo disp : repo2.Dispositivos()) {
			disp.presentate();
			System.out.println(disp.getClass());
		}
	}

	@Test
	public void ImportadorJsonZona() {
		RepositorioDeZonas repoZonas = RepositorioDeZonas.getinstance();
		repoZonas.cargarZonas();

		assertEquals(3, repoZonas.zonas().size());

		for (ZonaGeografica zona : repoZonas.zonas()) {

			System.out.println(zona.getNombre());
		}
	}

	@Test
	public void ImportadorJsonTransformador() {
		RepositorioDeTransformadores repoTransfo = RepositorioDeTransformadores.getinstance();
		repoTransfo.cargarTransformadores();
		assertEquals(5, repoTransfo.transformadores().size());

		for (Transformador transfo : repoTransfo.transformadores()) {

			System.out.println(transfo.getId());
		}
	}

	@Test
	public void ImportadorJsonCliente() {
		RepositorioDeClientes repoClientes = RepositorioDeClientes.getinstance();
		repoClientes.cargarClientes();
		assertEquals(4, repoClientes.clientes().size());

		for (Cliente cli : repoClientes.clientes()) {

			cli.presentate();
		}
	}

}
