package sge.regla;

import sge.dispositivo.Inteligente;

public class ActuadorApagar implements Actuador {

	private Inteligente dispositivo;

	public ActuadorApagar(Inteligente _dispositivo) {
		dispositivo = _dispositivo;
	}
	
	public ActuadorApagar() {
	}

	@Override
	public void ejecutarAccion() {
		dispositivo.apagar();
	}

	@Override
	public void dispositivo(Inteligente _dispositivo) {
		dispositivo = _dispositivo;		
	}
}
