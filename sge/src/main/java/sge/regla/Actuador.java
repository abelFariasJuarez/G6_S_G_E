package sge.regla;

import sge.dispositivo.Inteligente;

public interface Actuador {
	
	void ejecutarAccion();
	
	public void dispositivo(Inteligente _dispositivo);
}
