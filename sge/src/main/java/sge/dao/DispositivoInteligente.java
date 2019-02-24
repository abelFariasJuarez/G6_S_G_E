package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.DriverBasico;

@Entity
@DiscriminatorValue("I")
@Table(name="DispositivoInteligente")
public class DispositivoInteligente extends Inteligente {

	public DispositivoInteligente() {
		super();
	}

	public DispositivoInteligente(String _nombre, Double _consumoPorHora, String _idUserName, Boolean _bajoconsumo,
			Boolean _encendido) {
		super(_nombre, _consumoPorHora, _idUserName, _bajoconsumo);
	}

	public DispositivoInteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
	}

}
