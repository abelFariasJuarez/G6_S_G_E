package sge.driver;

import sge.dispositivo.Inteligente;

public class AccionPrender implements Accion {

	
	public void ejecutar(Inteligente dispo) {
		dispo.prender();
	}
}
