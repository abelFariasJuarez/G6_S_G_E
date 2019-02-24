package sge.modelo.valueobjects;

import java.util.function.BiFunction;

public abstract class ComparadorVO{

	BiFunction<Double, Double, Boolean> cmp;
	
	public boolean comparar(double valorActual, double valorEsperado) {
		return cmp.apply(valorActual,valorEsperado);
	}

	public ComparadorVO() {
	}
	
	
}
