package sge.driver;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sge.dispositivo.DispositivoInteligente;
import sge.dispositivo.Inteligente;
import sge.regla.*;
@SuppressWarnings("deprecation")
public class DriverBasico {

	ActuadorApagar adapterApagar;
	ActuadorPrender adapterPrender;
	ActuadorAhorro adapterAhorro;
	

	
	
	public void prender(Inteligente dispo) {
		adapterPrender.ejecutarAccion();
	}

	public void apagar(Inteligente dispo) {
		adapterApagar.ejecutarAccion();
	}

	public void ahorroDeEnergia(Inteligente dispo) {
		adapterAhorro.ejecutarAccion();
	}

	
	
}
