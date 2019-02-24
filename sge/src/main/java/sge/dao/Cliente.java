package sge.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("C")
@Table(name = "ClienteVO")
public class Cliente extends Usuario  {

	@Column(name = "tipodoc")
	private String tipodoc;
	@Column(name = "nrodoc")
	private Integer nrodoc;
	@Column(name = "telefono")
	private Integer telefono;
	@ManyToOne(cascade = CascadeType.ALL)
	private Categoria categoria;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	@Column(name = "puntos")
	private Integer puntos = 0;
	@ManyToOne(cascade = CascadeType.ALL)
	private Ubicacion ubicacion;
	@Column(name = "ahorroAutomatico")
	private boolean ahorroAutomatico = false; // o accion automatica o accione por si solo
	@OneToOne(cascade = CascadeType.ALL)
	private Accion accionParaMejorarEficiencia = new AccionApagar();// la orden de "apagar" (podria ser accion
																	// configurable)
	
	private  float consumo;

	public Cliente() {
	}

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;

	}

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, Ubicacion _ubi) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		ubicacion = _ubi;

	}

	public Cliente(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos,
			Ubicacion _ubi) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;
		ubicacion = _ubi;

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
	}

	public void removeDispositivo(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public boolean isAhorroAutomatico() {
		return ahorroAutomatico;
	}

	public void setAhorroAutomatico(boolean ahorroAutomatico) {
		this.ahorroAutomatico = ahorroAutomatico;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public Integer getNrodoc() {
		return nrodoc;
	}

	public void setNrodoc(Integer nrodoc) {
		this.nrodoc = nrodoc;
	}

	public Accion getAccionParaMejorarEficiencia() {
		return accionParaMejorarEficiencia;
	}

	public void setAccionParaMejorarEficiencia(Accion accionParaMejorarEficiencia) {
		this.accionParaMejorarEficiencia = accionParaMejorarEficiencia;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public float getConsumo() {
		return consumo;
	}

	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}
}
