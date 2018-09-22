package sge.modelo.regla.comparador;

public class Igual extends Comparador {

	public Igual() {
		cmp = (v1, v2) -> v1 == v2;
	}

}
