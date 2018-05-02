package sge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import sge.Dispositivo;

public class Cliente extends UsuarioSGE {

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;
	}

	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

	public String getTipoDoc() {
		return tipodoc;
	}

	public int getNroDoc() {
		return nrodoc;
	}

	public int getTelefono() {
		return telefono;
	}

	public Categoria getCategoria() {

		return categoria;
	}

	public void addDispositivo(Dispositivo dispositivo) {
		dispositivos.add(dispositivo);

	}

	public void removeDispositivo(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);

	}

	public boolean tengoAlgunDispositivoON() {
		return dispositivos.stream().anyMatch(dis -> dis.estoyON());

	}

	public Integer cantDispositivosON() {
		return (int) dispositivos.stream().filter(dis -> dis.estoyON()).count();
	}

	Integer cantDispositivosOFF() {
		return (int) dispositivos.stream().filter(dis -> !dis.estoyON()).count();
	}

	Integer cantDispositivos() {
		return dispositivos.size();
	}

	public void presentate() {
		System.out.println("nombre:" + nombre + "  " + "apellido:" + apellido + "  " + "FechaIngreso:"
				+ fechaIngreso + "  " + "username:" + username + "  " + "password:" + password + "\n"
				+ "Domicilio:" + domicilio + "  " + "Tipo Doc:" + tipodoc + "  " + "Nro Doc:" + nrodoc
				+ "" + "telefono:" +telefono + "  " + "Fecha de alta:" + fechaIngreso + "\n"
				+ "Dispositivos:");
	}

	public Float consumo() {
		return (float) this.dispositivos.stream().mapToDouble(dis -> dis.informarConsumo()).sum();
	}
}
