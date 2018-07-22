package sge.driver;

import sge.dispositivo.Inteligente;

public class AccionApagar implements Accion {
public void ejecutar(Inteligente dispo) {
	dispo.driver.ahorroDeEnergia(dispo);


	}
}
