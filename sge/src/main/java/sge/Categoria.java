package sge;

public class Categoria {
	String codigo;
	Float cargoFijo;
	Float cargoVariable;
	Float cotaInferior;
	Float cotaSuperior;

	public Categoria(String _cod, Float _fijo, Float _variable, Float _inf, Float _sup) {
		codigo = _cod;
		cargoFijo = _fijo;
		cargoVariable = _variable;
		cotaInferior = _inf;
		cotaSuperior = _sup;
	}

	public boolean estaEnTuRangoSuConsumo(Cliente unCliente) {
		return estaEnTuRango(unCliente.consumo());
	}

	public boolean estaEnTuRango(Float float1) {
		return this.mayorCotaInferior(float1) && this.menorCotaSuperior(float1);
	}

	private boolean mayorCotaInferior(Float float1) {
		return (cotaInferior == null) || cotaInferior < float1;
	}

	private boolean menorCotaSuperior(Float float1) {
		return (cotaSuperior == null) || cotaSuperior >= float1;
	}

	public Float CostoEstimado(Cliente user) {
		return (float) cargoFijo + cargoVariable * user.consumo();
	}

}
