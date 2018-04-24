package sge;

import java.time.LocalDate;
import java.util.Date;
//import java.util.Calendar;


public abstract class UsuarioSGE {
	String nombre;
	String apellido;
	String domicilio;
	LocalDate fechaIngreso;
	String username;
	String password;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaingreso(LocalDate fechaingreso) {
		this.fechaIngreso = fechaingreso;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
