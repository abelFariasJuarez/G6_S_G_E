package sge.modelo.regla;

import sge.modelo.dispositivo.Inteligente;

public class ActuadorPrender implements AdapterActuador {



	@Override
	public void ejecutarAccion() {

		System.out.printf("Accion sobre el dispo fisico");

	}


}
