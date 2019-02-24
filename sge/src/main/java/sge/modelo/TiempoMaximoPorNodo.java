package sge.modelo;

import sge.modelo.valueobjects.DispositivoVO;

public class TiempoMaximoPorNodo {
	private DispositivoVO nodo;
	private double horas;
	
	public TiempoMaximoPorNodo(DispositivoVO _dispo, double _hora) {
		nodo = _dispo;
		horas = _hora;
	}
	
	public DispositivoVO nodo()
	{
		return nodo;
	}
	
	public double horas()
	{
		return horas;
	}

}


