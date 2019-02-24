package sge.modelo.valueobjects;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.dao.Persistible;

public class CategoriaVO {

	String codigo;
	Float cargoFijo;
	Float cargoVariable;
	Float cotaInferior;
	Float cotaSuperior;

	public CategoriaVO() {
	}
	
	public CategoriaVO(String _cod, Float _fijo, Float _variable, Float _inf, Float _sup) {
		codigo = _cod;
		cargoFijo = _fijo;
		cargoVariable = _variable;
		cotaInferior = _inf;
		cotaSuperior = _sup;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(Float cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public Float getCargoVariable() {
		return cargoVariable;
	}

	public void setCargoVariable(Float cargoVariable) {
		this.cargoVariable = cargoVariable;
	}

	public Float getCotaInferior() {
		return cotaInferior;
	}

	public void setCotaInferior(Float cotaInferior) {
		this.cotaInferior = cotaInferior;
	}

	public Float getCotaSuperior() {
		return cotaSuperior;
	}

	public void setCotaSuperior(Float cotaSuperior) {
		this.cotaSuperior = cotaSuperior;
	}

	public boolean estaEnTuRangoSuConsumo(ClienteVO unCliente) {
		return estaEnTuRango(unCliente.consumo());
	}

	public boolean estaEnTuRango(Float float1) {
		return this.mayorCotaInferior(float1) && this.menorCotaSuperior(float1);
	}

	private boolean mayorCotaInferior(Float float1) {
		return (cotaInferior == 0f) || cotaInferior < float1;
	}

	private boolean menorCotaSuperior(Float float1) {
		return (cotaSuperior == Float.MAX_VALUE) || cotaSuperior >= float1;
	}

	public Float CostoEstimado(ClienteVO user) {
		return (float) cargoFijo + cargoVariable * user.consumo();
	}

	public double CostoEstimado(ClienteVO user, LocalDateTime inicioPeriodo, LocalDateTime finPeriodo) {
		return (float) cargoFijo + cargoVariable * user.consumoEnPeriodo(inicioPeriodo, finPeriodo);
	}

}
