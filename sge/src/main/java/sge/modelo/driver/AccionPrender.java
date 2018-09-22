package sge.modelo.driver;

import sge.modelo.dispositivo.Inteligente;

public class AccionPrender implements Accion {

	
	public void ejecutar(Inteligente dispo) {
		dispo.prender();
	}
}
