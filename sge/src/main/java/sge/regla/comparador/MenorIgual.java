package sge.regla.comparador;

public class MenorIgual extends Comparador {
	public MenorIgual() {
		cmp = (v1, v2) -> v1 <= v2;
	}
}
