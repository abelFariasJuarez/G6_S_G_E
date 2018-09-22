package sge.modelo.hogareficiente;

import java.util.ArrayList;
import java.util.List;

import sge.modelo.dispositivo.Dispositivo;

public class Recomendacion {
	double horasTotalesMensuales;
	List<TiempoMaximoPorNodo> horasMaximasPorNodo = new ArrayList<TiempoMaximoPorNodo>();

	public void agregarTiempoMaximoPorNodo(Dispositivo dispositivo, double horas) {
		TiempoMaximoPorNodo tmpn = new TiempoMaximoPorNodo(dispositivo, horas);
		horasMaximasPorNodo.add(tmpn);
	}

	public void horasTotalesMensuales(Double value) {
		horasTotalesMensuales = value;
	}

	public double horasMaximasPara(Dispositivo dispositivo) {
		return horasMaximasPorNodo.stream().filter(h -> h.nodo().equals(dispositivo)).findFirst().get().horas();
	}

	public boolean esEficiente(Dispositivo i, double consumoEnPeriodoDe) {
		return (consumoEnPeriodoDe <= this.horasMaximasPara(i));
	}

}
