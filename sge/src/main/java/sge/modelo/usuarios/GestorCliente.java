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


	public Repositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public GestorCliente() {
		
	}

	public void cargarClientesZonasTransformadores() {
		getRepoClientes().cargarClientesDesdeJson();
		getRepoZonas().cargarZonas();
		getRepoTransformadores().cargarTransformadores();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaGeografica zona1 : getRepoZonas().all()) {
			List<Transformador> pepe = zona1.getTransformadores();
			for (Transformador trans1 : getRepoTransformadores().all()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);
				}
			}
			getRepoZonas().persistir(zona1);
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

	public Zonas getRepoZonas() {
		return repositorio.zonas();
	}

	public Clientes getRepoClientes() {
		return repositorio.clientes();
	}

	public Transformadores getRepoTransformadores() {
		return repositorio.transformadores();
	}

}
