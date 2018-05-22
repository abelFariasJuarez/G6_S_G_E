package sge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import sge.dispositivo.*;

public class Cliente extends UsuarioSGE {
	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	private Integer puntos = 0;

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;

	}
	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos=_dispositivos;

	}
	
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
		dispositivo.setInstanteDeCreacion(LocalDateTime.now());

	}

	public void removeDispositivo(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);

	}

	public Stream<Dispositivo> misInteligentes() {
		return dispositivos.stream().filter(dis -> dis instanceof Inteligente);
	}

	public boolean tengoAlgunDispositivoON() {
		return this.misInteligentes().anyMatch(dis -> ((Inteligente) dis).estoyON());
	}

	public Integer cantDispositivosON() {
		return (int) this.misInteligentes().filter(dis -> ((Inteligente) dis).estoyON()).count();
	}

	public Integer cantDispositivosOFF() {
		return (int) this.misInteligentes().filter(dis -> ((Inteligente) dis).estoyOFF()).count();
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
		System.out.println("nombre:" + nombre + "  " + "apellido:" + apellido + "  " + "FechaIngreso:" + fechaIngreso
				+ "  " + "username:" + username + "  " + "password:" + password + "\n" + "Domicilio:" + domicilio + "  "
				+ "Tipo Doc:" + tipodoc + "  " + "Nro Doc:" + nrodoc + "" + "telefono:" + telefono + "  "
				+ "Fecha de alta:" + fechaIngreso + "\n" + "Dispositivos:" + "");
		this.presentarDispositivos();
	}

	public void presentarDispositivos() {

		dispositivos.forEach(d -> d.presentate());
	}

	public DispositivoConModulo agrega_modulo_a_estandar(DispositivoEstandar comun) {
		if (!dispositivos.contains(comun))
			throw new RuntimeException("solo se puede convertir dispositivos registrados");

		DispositivoConModulo conModulo = new DispositivoConModulo(comun, false);
		dispositivos.remove(comun);
		dispositivos.add(conModulo);

		this.sumarPuntos(DispositivoConModulo.puntos());

		return conModulo;
	}

	private void sumarPuntos(Integer _puntos) {
		puntos += _puntos;
	}

	public Float consumo() {
		return (float) dispositivos.stream().mapToDouble(dis -> dis.informarConsumo()).sum();
	}

	public Integer getPuntos() {
		return puntos;
	}

	public Double consumoEnPeriodo(LocalDateTime inicioPeriodo, LocalDateTime finPeriodo) {
		return dispositivos.stream().mapToDouble(dis -> dis.consumo_periodo(inicioPeriodo, finPeriodo)).sum();
	}
}
