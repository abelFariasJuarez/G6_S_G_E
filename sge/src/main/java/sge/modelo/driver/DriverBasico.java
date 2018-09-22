package sge.modelo.driver;

import sge.modelo.dispositivo.Inteligente;
import sge.modelo.regla.*;

public class DriverBasico {

	ActuadorApagar adapterApagar;
	ActuadorPrender adapterPrender;
	ActuadorAhorro adapterAhorro;
	
	public DriverBasico(ActuadorApagar adapterApagar, ActuadorPrender adapterPrender, ActuadorAhorro adapterAhorro) {
		super();
		this.adapterApagar = adapterApagar;
		this.adapterPrender = adapterPrender;
		this.adapterAhorro = adapterAhorro;
	}

	public void prender(Inteligente dispo) {
		adapterPrender.ejecutarAccion();
	}

	public void apagar(Inteligente dispo) {
		adapterApagar.ejecutarAccion();
	}

	public void ahorroDeEnergia(Inteligente dispo) {
		adapterAhorro.ejecutarAccion();
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
