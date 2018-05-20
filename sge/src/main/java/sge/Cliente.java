package sge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import sge.dispositivo.*;

public class Cliente extends UsuarioSGE {

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;
	}
	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
	
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

	public Stream<Dispositivo> misInteligentes()
	{
		return dispositivos.stream().filter(dis->dis instanceof IInteligente);
	}
	
	public boolean tengoAlgunDispositivoON() {
		return  this.misInteligentes().anyMatch(dis->( (IInteligente) dis).estoyON());
	}

	public Integer cantDispositivosON() {
		return  (int)  this.misInteligentes().filter(dis-> ((IInteligente) dis).estoyON()).count();
	}

	public Integer cantDispositivosOFF() {
		return  (int)  this.misInteligentes().filter(dis->((IInteligente) dis).estoyOFF()).count();
	}
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}
	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
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
