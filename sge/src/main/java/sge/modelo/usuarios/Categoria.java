package sge.modelo.usuarios;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Table(name = "Categoria")
public class Categoria extends Persistible {

	@Column(name = "codigo")
	String codigo;
	@Column(name = "cargoFijo")
	Float cargoFijo;
	@Column(name = "cargoVariable")
	Float cargoVariable;
	@Column(name = "cotaInferior")
	Float cotaInferior;
	@Column(name = "cotaSuperior")
	Float cotaSuperior;

	public Categoria() {
	}
	
	public Categoria(String _cod, Float _fijo, Float _variable, Float _inf, Float _sup) {
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

	public boolean estaEnTuRangoSuConsumo(Cliente unCliente) {
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

	public Float CostoEstimado(Cliente user) {
		return (float) cargoFijo + cargoVariable * user.consumo();
	}

	public double CostoEstimado(Cliente user, LocalDateTime inicioPeriodo, LocalDateTime finPeriodo) {
		return (float) cargoFijo + cargoVariable * user.consumoEnPeriodo(inicioPeriodo, finPeriodo);
	}

}
