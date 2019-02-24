package sge.modelo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.ZonaVO;

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
		//getRepoClientes().cargarGuardar();
		//getRepoZonas().cargarGuardar();
		//getRepoTransformadores().cargarGuardar();
	}

	public void transformadoresAsignacionZona() {

		for (ZonaVO zona1 : this.getRepositorio().allZonas()) {
			for (TransformadorVO trans1 : this.getRepositorio().allTransformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);
				}
			}
			getRepositorio().persistir(zona1);
		}

	}

	public void asignarClientesATransformadores() {
		
		for (ClienteVO clienteVO : this.getRepositorio().allClientes()) {
			ZonaVO zona = this.getRepositorio().allZonas().stream().filter(s -> s.pertenece(clienteVO)).findFirst()
					.get();
			
			TransformadorVO trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(clienteVO)));
			
			trans.getClientes().add(clienteVO);

			this.getRepositorio().persistir(trans);
		}
	}

	public void mejorarEficienciaHogares() {
		this.mejorarEficienciaHogaresA(this.getRepositorio().allClientes());
	}

	public void mejorarEficienciaHogaresA(List<ClienteVO> clienteVOs) {
		clienteVOs.stream().filter(c -> c.isAhorroAutomatico() && c.canYouGetMejorCombinacionDispositivos())
				.forEach(c -> c.mejorarEficienciaHogar());
	}
	
	public void mejorarEficienciaHogaresA(ClienteVO cli) {
		if ( cli.isAhorroAutomatico() && cli.canYouGetMejorCombinacionDispositivos())
			cli.mejorarEficienciaHogar();
	}

}
