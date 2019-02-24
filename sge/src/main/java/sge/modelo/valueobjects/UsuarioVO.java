package sge.modelo.valueobjects;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class UsuarioVO implements Serializable {

	private String nombre;
	private String apellido;
	private String domicilio;
	private LocalDate fechaIngreso;
	private String username;
	private String password;

	public UsuarioVO() {
	}

	public UsuarioVO(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
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
