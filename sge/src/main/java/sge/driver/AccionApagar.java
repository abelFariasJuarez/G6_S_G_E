package sge.driver;

import sge.dispositivo.Inteligente;

public class AccionApagar {
void ejecutar(Inteligente dispo) {
	dispo.driver.ahorroDeEnergia(dispo);

	System.out.printf("Apagar Dispo Fisico");

	}
}
