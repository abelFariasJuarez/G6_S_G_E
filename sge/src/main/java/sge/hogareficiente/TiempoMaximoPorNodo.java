package sge.hogareficiente;

import sge.dispositivo.Dispositivo;

public class TiempoMaximoPorNodo {
	private Dispositivo nodo;
	private double horas;
	
	public TiempoMaximoPorNodo(Dispositivo _dispo, double _hora) {
		nodo = _dispo;
		horas = _hora;
	}
	
	public Dispositivo nodo()
	{
		return nodo;
	}
	
	public double horas()
	{
		return horas;
	}

}


