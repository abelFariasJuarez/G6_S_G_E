package sge.regla;

import sge.dispositivo.Inteligente;

public class ActuadorPrender implements Actuador {

	private Inteligente dispositivo;

	public ActuadorPrender(Inteligente _dispositivo) {
		dispositivo = _dispositivo;
	}

	@Override
	public void ejecutarAccion() {
		dispositivo.prender();
	}

	@Override
	public void dispositivo(Inteligente _dispositivo) {
		dispositivo = _dispositivo;	
	}

}
