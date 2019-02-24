package sge.dao;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
@Table(name = "AdministradorVO")
public class Administrador extends Usuario {

	@Column(name = "nroAdminID")
	Integer nroAdminID;

	public Integer getNroAdminID() {
		return nroAdminID;
	}

	public void setNroAdminID(Integer nroAdminID) {
		this.nroAdminID = nroAdminID;
	}

	public Administrador() {
	}

	public Administrador(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);

	}
}
