package sge;

import static org.junit.Assert.*;

import org.junit.Test;

import sge.repositorios.RepositorioDeClientes;
import sge.repositorios.RepositorioDeDispositivos;
import sge.repositorios.RepositorioDeTransformadores;
import sge.repositorios.RepositorioDeZonas;
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;

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
