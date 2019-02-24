package sge.modelo;

import sge.modelo.valueobjects.InteligenteVO;

public class DriverBasico {

	InteligenteVO miDispositivo;
	ActuadorApagar adapterApagar;
	ActuadorPrender adapterPrender;
	ActuadorAhorro adapterAhorro;
	
	public DriverBasico() {	
		/*Creamos los drives con acciones basicas por default, 
		 * luego si un fabricante quiere agregar mas acciones, slo se tendra que agregar mas clases*/
		this.adapterApagar = new ActuadorApagar();
		this.adapterPrender = new ActuadorPrender();
		this.adapterAhorro = new ActuadorAhorro();
	}

	public void prender() {
		miDispositivo.prender();
		adapterPrender.ejecutarAccion();
	}

	public void apagar() {
		miDispositivo.apagar();
		adapterApagar.ejecutarAccion();
	}

	public void ahorroDeEnergia() {
		miDispositivo.ahorroDeEnergia();
		adapterAhorro.ejecutarAccion();
	}

	
	public InteligenteVO getMiDispositivo() {
		return miDispositivo;
	}

	public void setMiDispositivo(InteligenteVO miDispositivo) {
		this.miDispositivo = miDispositivo;
	}	
	
	public ActuadorApagar getAdapterApagar() {
		return adapterApagar;
	}

	public void setAdapterApagar(ActuadorApagar adapterApagar) {
		this.adapterApagar = adapterApagar;
	}

	public ActuadorPrender getAdapterPrender() {
		return adapterPrender;
	}

	public void setAdapterPrender(ActuadorPrender adapterPrender) {
		this.adapterPrender = adapterPrender;
	}

	public ActuadorAhorro getAdapterAhorro() {
		return adapterAhorro;
	}

	public void setAdapterAhorro(ActuadorAhorro adapterAhorro) {
		this.adapterAhorro = adapterAhorro;
	}

	
	
}
