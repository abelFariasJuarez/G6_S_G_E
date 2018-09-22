package sge.modelo.regla.comparador;

public class Menor extends Comparador {
	
	public Menor() {
		cmp = (v1, v2) -> v1 < v2;
	}
	
}
