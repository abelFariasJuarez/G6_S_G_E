package sge.modelo.driver;

import sge.modelo.dispositivo.Inteligente;

public class AccionAhorrro implements Accion {
public void ejecutar(Inteligente dispo) {
	
	dispo.ahorroDeEnergia();

	}
}
