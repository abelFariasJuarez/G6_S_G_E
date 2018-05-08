package sge;

public class Categoria {
	String codigo;
	Float cargoFijo;
	Float cargoVariable;
	Float cotaInferior;
	Float cotaSuperior;

	public Categoria(String codigo, Float cargoFijo, Float cargoVariable, Float cotaInferior, Float cotaSuperior) {
		this.codigo = codigo;
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.cotaInferior = cotaInferior;
		this.cotaSuperior = cotaSuperior;
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
		return (float) cargoFijo+cargoVariable*user.consumo();
	}
	
}
