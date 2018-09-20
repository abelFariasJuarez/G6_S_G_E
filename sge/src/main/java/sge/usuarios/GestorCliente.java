package sge.usuarios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.posicionamiento.*;
import sge.repositorios.RepositorioDeClientes;
import sge.repositorios.RepositorioDeTransformadores;
import sge.repositorios.RepositorioDeZonas;

public class GestorCliente {

	private RepositorioDeClientes repoClientes = RepositorioDeClientes.getinstance();
	private RepositorioDeZonas repoZonas = RepositorioDeZonas.getinstance();
	private RepositorioDeTransformadores repoTransformadores = RepositorioDeTransformadores.getinstance();

	public GestorCliente() {
		getRepoClientes().cargarClientes();
		getRepoZonas().cargarZonas();
		getRepoTransformadores().cargarTransformadores();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaGeografica zona1 : getRepoZonas().zonas()) {
			for (Transformador trans1 : getRepoTransformadores().transformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);

				}
			}
		}

	}

	public void asignarClientesATransformadores() {
		for (Cliente cliente : getRepoClientes().clientes()) {
			ZonaGeografica zona = getRepoZonas().zonas().stream().filter(s -> s.pertenece(cliente)).findFirst().get();
			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(cliente)));
			trans.getClientes().add(cliente);
		}
	}

	public void mejorarEficienciaHogares() {
		this.mejorarEficienciaHogaresA(getRepoClientes().clientes());
	}

	public void mejorarEficienciaHogaresA(List<Cliente> clientes) {
		clientes.stream().filter(c -> c.ahorroAutomaticoActivo() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}

	public RepositorioDeZonas getRepoZonas() {
		return repoZonas;
	}

	public void setRepoZonas(RepositorioDeZonas repoZonas) {
		this.repoZonas = repoZonas;
	}

	public RepositorioDeClientes getRepoClientes() {
		return repoClientes;
	}

	public void setRepoClientes(RepositorioDeClientes repoClientes) {
		this.repoClientes = repoClientes;
	}

	public RepositorioDeTransformadores getRepoTransformadores() {
		return repoTransformadores;
	}

	public void setRepoTransformadores(RepositorioDeTransformadores repoTransformadores) {
		this.repoTransformadores = repoTransformadores;
	}

}
