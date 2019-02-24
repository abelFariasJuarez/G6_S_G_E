package sge.modelo;

public class ActuadorAhorro implements AdapterActuador {

	@Override
	public void ejecutarAccion() {
		System.out.printf("Accion sobre el dispo fisico");
	}
	
}
