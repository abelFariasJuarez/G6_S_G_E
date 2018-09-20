package sge;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.Cliente;
import sge.posicionamiento.*;
import sge.repositorios.RepositorioDeClientes;
import sge.repositorios.RepositorioDeTransformadores;
import sge.repositorios.RepositorioDeZonas;

public class GestorCliente {

	RepositorioDeClientes repoClientes = RepositorioDeClientes.getinstance();
	RepositorioDeZonas repoZonas = RepositorioDeZonas.getinstance();
	RepositorioDeTransformadores repoTransformadores = RepositorioDeTransformadores.getinstance();

	public GestorCliente() {
		repoClientes.cargarClientes();
		repoZonas.cargarZonas();
		repoTransformadores.cargarTransformadores();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaGeografica zona1 : repoZonas.zonas()) {
			for (Transformador trans1 : repoTransformadores.transformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);

				}
			}
		}

	}

	public void asignarClientesATransformadores() {
		for (Cliente cliente : repoClientes.clientes()) {
			ZonaGeografica zona = repoZonas.zonas().stream().filter(s -> s.pertenece(cliente)).findFirst().get();
			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(cliente)));
			trans.getClientes().add(cliente);
		}
	}

	public void mejorarEficienciaHogares() {
		this.mejorarEficienciaHogaresA(repoClientes.clientes());
	}

	public void mejorarEficienciaHogaresA(List<Cliente> clientes) {
		clientes.stream().filter(c -> c.ahorroAutomaticoActivo() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}

}
