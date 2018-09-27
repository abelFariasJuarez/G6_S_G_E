package sge.modelo.regla;

import sge.modelo.dispositivo.Inteligente;

public class AccionAhorrro implements Accion {
	
	public void ejecutar(Inteligente dispo) {
		dispo.getDriver().ahorroDeEnergia();
	}
}
