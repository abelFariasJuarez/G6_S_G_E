package sge.regla;

import sge.dispositivo.Inteligente;

public class ActuadorAhorro implements Actuador {

	private Inteligente dispositivo;

	public ActuadorAhorro(Inteligente _dispositivo) {
		dispositivo = _dispositivo;
	}

	@Override
	public void ejecutarAccion() {
		dispositivo.ahorroDeEnergia();
	}

}
