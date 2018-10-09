package sge;

import static org.junit.Assert.*;

import org.junit.Test;

import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Dispositivos;
import sge.repositorios.Transformadores;
import sge.repositorios.RepositorioDeZonas;
import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;

public class TestImportador {

	@Test
	public void ImportadorJsonDispositivo() {
		Dispositivos repoDispositivos = new Repositorio().dispositivos();
		repoDispositivos.cargarDispositivos("todos");

		assertEquals(24, repoDispositivos.getDispositivos().size());

		for (Dispositivo disp : repoDispositivos.getDispositivos()) {
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
		Transformadores repoTransfo = new Repositorio().transformadores();
		repoTransfo.cargarTransformadores();
		assertEquals(5, repoTransfo.getTransformadores().size());

		for (Transformador transfo : repoTransfo.getTransformadores()) {

			System.out.println(transfo.getId());
		}
	}

	@Test
	public void ImportadorJsonCliente() {
		Clientes repoClientes = (new Repositorio()).clientes();
		repoClientes.cargarClientes();
		assertEquals(4, repoClientes.getClientes().size());

		for (Cliente cli : repoClientes.getClientes()) {

			cli.presentate();
		}
	}

}
