package sge.modelo.usuarios;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

import sge.modelo.Persistible;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "userType")
@Table(name = "UsuarioSGE")
public abstract class UsuarioSGE extends Persistible {
	
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "apellido")
	private String apellido;
	@Column(name = "domicilio")
	private String domicilio;
	@Column(name = "fechaIngreso")
	private LocalDate fechaIngreso;
	@SerializedName("username")
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	public UsuarioSGE() {
	}

	public UsuarioSGE(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password) {
		nombre = _nombre;
		apellido = _apellido;
		domicilio = _domicilio;
		fechaIngreso = _fechaIngreso;
		username = _username;
		password = _password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String _nombre) {
		nombre = _nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String _apellido) {
		apellido = _apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String _domicilio) {
		domicilio = _domicilio;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaingreso(LocalDate _fechaingreso) {
		fechaIngreso = _fechaingreso;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String _username) {
		username = _username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		password = _password;
	}
}
