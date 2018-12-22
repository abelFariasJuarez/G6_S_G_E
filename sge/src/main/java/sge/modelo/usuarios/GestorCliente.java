package sge.modelo.usuarios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.modelo.posicionamiento.*;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;
import sge.repositorios.Zonas;

public class GestorCliente {
	private Repositorio repositorio = new Repositorio();
	private Clientes repoClientes = repositorio.clientes();
	private Zonas repoZonas = repositorio.zonas();
	private Transformadores repoTransformadores = repositorio.transformadores();

	public GestorCliente() {
		
	}

	public void cargarClientesZonasTransformadores() {
		getRepoClientes().cargarClientesDesdeJson();
		getRepoZonas().cargarZonas();
		getRepoTransformadores().cargarTransformadores();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaGeografica zona1 : getRepoZonas().getZonas()) {
			for (Transformador trans1 : getRepoTransformadores().getTransformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);

				}
			}
		}

	}

	public void asignarClientesATransformadores() {
		for (Cliente cliente : getRepoClientes().getClientesJson()) {
			ZonaGeografica zona = getRepoZonas().getZonas().stream().filter(s -> s.pertenece(cliente)).findFirst()
					.get();
			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(cliente)));
			trans.getClientes().add(cliente);
		}
	}

	public void mejorarEficienciaHogares() {
		this.mejorarEficienciaHogaresA(getRepoClientes().getClientesJson());
	}

	public void mejorarEficienciaHogaresA(List<Cliente> clientes) {
		clientes.stream().filter(c -> c.isAhorroAutomatico() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}
	
	public void mejorarEficienciaHogaresA(Cliente cli) {
		if ( cli.isAhorroAutomatico() && cli.canYouGetMejorCombinacionDispositivos())
			cli.mejorarEficienciaHogar();
	}

	public Zonas getRepoZonas() {
		return repoZonas;
	}

	public void setRepoZonas(Zonas repoZonas) {
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
