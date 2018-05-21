package sge.regla;

import sge.dispositivo.Inteligente;

public class ActuadorApagar implements Actuador {

	public ActuadorApagar(Inteligente _dispositivo) {
		dispositivo=_dispositivo;
	}

	private Inteligente dispositivo;

	@Override
	public void ejecutarAccion() {
		dispositivo.apagar();
		
	}
}
