package sge.modelo.regla;

import java.util.ArrayList;
import java.util.List;

public class AvisoCambioSensor {
	public static List<CambiaPorElSensor> observadores=new ArrayList<CambiaPorElSensor>();
	public void agregar(CambiaPorElSensor observador) {
		observadores.add(observador);
	}
	public void quitar(CambiaPorElSensor observador) {
		observadores.remove(observador);
	}
	public void notificar() {
		for (int i=0;i<observadores.size();i++)
		{
			observadores.get(i).update();
		}
	}
}
