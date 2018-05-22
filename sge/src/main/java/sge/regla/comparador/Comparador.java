package sge.regla.comparador;

import java.util.function.BiFunction;

public abstract class Comparador {
	BiFunction<Double, Double, Boolean> cmp;
	
	public boolean comparar(double valorActual, double valorEsperado) {
		return cmp.apply(valorActual,valorEsperado);
	}
}
