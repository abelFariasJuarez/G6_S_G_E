package sge.modelo.driver;

import sge.modelo.dispositivo.Inteligente;

public class AccionApagar implements Accion {
public void ejecutar(Inteligente dispo) {
	dispo.apagar();
	}
}
