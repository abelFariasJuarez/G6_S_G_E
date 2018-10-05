package sge.modelo.usuarios;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
@Table(name = "Administrador")
public class Administrador extends UsuarioSGE {

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

	public int cantMesesAdministrando() {
		LocalDate today = LocalDate.now();
		Period age = Period.between(this.getFechaIngreso(), today);
		int months = age.getMonths() + (age.getYears() * 12);
		return months;
	}

}
