package sge.driver;

import sge.dispositivo.Inteligente;

public class AccionAhorrro implements Accion {
public void ejecutar(Inteligente dispo) {
	
	dispo.ahorroDeEnergia();

	}
}
