package sge.driver;

import sge.dispositivo.Inteligente;

public class AccionAhorrro {
void ejecutar(Inteligente dispo) {
	
	dispo.driver.ahorroDeEnergia(dispo);
	System.out.printf("Ahorro de energia Dispo Fisico");

	}
}
