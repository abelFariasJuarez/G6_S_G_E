package sge.modelo;

public class ActuadorPrender implements AdapterActuador {

	@Override
	public void ejecutarAccion() {
		System.out.printf("Accion sobre el dispo fisico");
	}

}
