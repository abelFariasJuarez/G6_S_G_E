package sge.modelo.regla.comparador;

public class MayorIgual extends Comparador {
	public MayorIgual() {
		cmp = (v1, v2) -> v1 >= v2;
	}
}
