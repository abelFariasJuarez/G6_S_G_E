package sge;

public class Categoria {
	String codigo;
	Float cargoFijo;
	Float cargoVariable;
	Float cotaInferior;
	Float cotaSuperior;


	public Categoria(String cod, Float fijo, Float variable, Float inf, Float sup) {
		this.codigo = cod;
		this.cargoFijo = fijo;
		this.cargoVariable = variable;
		this.cotaInferior = inf;
		this.cotaSuperior = sup;
	}

	public boolean estaEnTuRangoSuConsumo(Cliente unCliente) {
		return estaEnTuRango(unCliente.consumo());
	}

	public boolean estaEnTuRango(Float float1) {
		return this.mayorCotaInferior(float1) && this.menorCotaSuperior(float1);
	}

	private boolean mayorCotaInferior(Float float1) {
	    return (this.cotaInferior == null) || this.cotaInferior < float1; 
	}

	private boolean menorCotaSuperior(Float float1) {
		return (this.cotaSuperior == null) || this.cotaSuperior >= float1;
	}
}
