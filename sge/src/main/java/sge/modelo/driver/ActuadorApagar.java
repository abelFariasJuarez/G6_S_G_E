package sge.modelo.driver;

import sge.modelo.dispositivo.Inteligente;

public class ActuadorApagar implements AdapterActuador {

	@Override
	public void ejecutarAccion() {
		System.out.printf("Accion sobre el dispo fisico");
	}


}
