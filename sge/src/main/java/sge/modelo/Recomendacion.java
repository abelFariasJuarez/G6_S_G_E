package sge.modelo;

import java.util.ArrayList;
import java.util.List;

import sge.modelo.valueobjects.DispositivoVO;

public class Recomendacion {
	double horasTotalesMensuales;
	List<TiempoMaximoPorNodo> horasMaximasPorNodo = new ArrayList<TiempoMaximoPorNodo>();

	public void agregarTiempoMaximoPorNodo(DispositivoVO dispositivo, double horas) {
		TiempoMaximoPorNodo tmpn = new TiempoMaximoPorNodo(dispositivo, horas);
		horasMaximasPorNodo.add(tmpn);
	}

	public double getHorasTotalesMensuales() {
		return horasTotalesMensuales;
	}

	public void setHorasTotalesMensuales(double horasTotalesMensuales) {
		this.horasTotalesMensuales = horasTotalesMensuales;
	}

	public void horasTotalesMensuales(Double value) {
		horasTotalesMensuales = value;
	}

	public double horasMaximasPara(DispositivoVO dispositivo) {
		return horasMaximasPorNodo.stream().filter(h -> h.nodo().equals(dispositivo)).findFirst().get().horas();
	}

	public boolean esEficiente(DispositivoVO i, double consumoEnPeriodoDe) {
		return (consumoEnPeriodoDe <= this.horasMaximasPara(i));
	}

}
