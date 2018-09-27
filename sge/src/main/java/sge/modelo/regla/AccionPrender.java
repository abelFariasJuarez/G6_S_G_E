package sge.modelo.regla;

import sge.modelo.dispositivo.Inteligente;

public class AccionPrender implements Accion {

	
	public void ejecutar(Inteligente dispo) {
		dispo.getDriver().prender();
	}
}
