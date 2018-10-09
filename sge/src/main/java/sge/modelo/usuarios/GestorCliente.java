package sge.modelo.usuarios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.modelo.posicionamiento.*;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;
import sge.repositorios.RepositorioDeZonas;

public class GestorCliente {
	private Repositorio repositorio = new Repositorio();
	private Clientes repoClientes = repositorio.clientes();
	private RepositorioDeZonas repoZonas = RepositorioDeZonas.getinstance();
	private Transformadores repoTransformadores = repositorio.transformadores();

	public GestorCliente() {
		cargarClientesZonasTransformadores();
	}

	public void cargarClientesZonasTransformadores() {
		getRepoClientes().cargarClientes();
		getRepoZonas().cargarZonas();
		getRepoTransformadores().cargarTransformadores();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaGeografica zona1 : getRepoZonas().zonas()) {
			for (Transformador trans1 : getRepoTransformadores().getTransformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);

				}
			}
		}

	}

	public void asignarClientesATransformadores() {
		for (Cliente cliente : getRepoClientes().getClientes()) {
			ZonaGeografica zona = getRepoZonas().zonas().stream().filter(s -> s.pertenece(cliente)).findFirst().get();
			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(cliente)));
			trans.getClientes().add(cliente);
		}
	}

	public void mejorarEficienciaHogares() {
		this.mejorarEficienciaHogaresA(getRepoClientes().getClientes());
	}

	public void mejorarEficienciaHogaresA(List<Cliente> clientes) {
		clientes.stream().filter(c -> c.isAhorroAutomatico() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}

	public RepositorioDeZonas getRepoZonas() {
		return repoZonas;
	}

	public void setRepoZonas(RepositorioDeZonas repoZonas) {
		this.repoZonas = repoZonas;
	}

	public Clientes getRepoClientes() {
		return repoClientes;
	}

	public void setRepoClientes(Clientes repoClientes) {
		this.repoClientes = repoClientes;
	}

	public Transformadores getRepoTransformadores() {
		return repoTransformadores;
	}

	public void setRepoTransformadores(Transformadores repoTransformadores) {
		this.repoTransformadores = repoTransformadores;
	}

}
