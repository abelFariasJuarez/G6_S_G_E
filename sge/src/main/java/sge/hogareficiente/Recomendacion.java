package sge.hogareficiente;

import sge.dispositivo.Dispositivo;

public class Recomendacion {
	private Dispositivo nodo;
	private double horas;
	
	public Recomendacion(Dispositivo _dispo, double _hora) {
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


