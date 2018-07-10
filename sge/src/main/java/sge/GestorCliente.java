package sge;

import java.util.Collections;
import java.util.Comparator;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeTransformadores;
import Repositorios.RepositorioDeZonas;
import posicionamiento.*;
import sge.Cliente;

public class GestorCliente {

	RepositorioDeClientes repoClientes = RepositorioDeClientes.getinstance();
	RepositorioDeZonas repoZonas = RepositorioDeZonas.getinstance();
	RepositorioDeTransformadores repoTransformadores = RepositorioDeTransformadores.getinstance();

	private GestorCliente() {
		repoClientes.cargarClientes();
		repoZonas.cargarZonas();
		this.transformadoresAsignaciónZona();
	}

	/*
	 * public void asignarClienteATransformador(Cliente cliente) {
	 * 
	 * ZonaGeografica
	 * zona=repoZonas.zonas.stream().filter(s->s.pertenece(cliente)).findFirst().get
	 * ();
	 * 
	 * Transformador trans =
	 * Collections.min(zona.getTransformadores(),Comparator.comparing(t->t.Distancia
	 * (cliente))); trans.add(cliente);
	 * 
	 * }
	 */

	public void transformadoresAsignaciónZona() {
		repoZonas.bajaTransformadores();
		repoTransformadores.cargarTransformadores();
		for (ZonaGeografica zona1 : repoZonas.zonas()) {
			for (Transformador trans1 : repoTransformadores.transformadores) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);
					repoTransformadores.transformadores.remove(trans1);
				}
			}
		}

	}

	public void asignarClientesATransformadores() {
		for (Cliente cliente : repoClientes.clientes) {
			ZonaGeografica zona = repoZonas.zonas().stream().filter(s -> s.pertenece(cliente)).findFirst().get();
			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(cliente)));
			trans.add(cliente);
		}
	}

	public void mejorarEficienciaHogares() {

		repoClientes.clientes().stream()
				.filter(c -> c.ahorroAutomaticoActivo() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}

}
