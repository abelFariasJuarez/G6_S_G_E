package sge.modelo.usuarios;

import java.io.Serializable;
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

import sge.modelo.IPersistible;
import sge.modelo.dispositivo.*;
import sge.modelo.driver.ActuadorAhorro;
import sge.modelo.driver.ActuadorApagar;
import sge.modelo.driver.ActuadorPrender;
import sge.modelo.driver.DriverBasico;
import sge.modelo.hogareficiente.*;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.regla.*;

@Entity
@DiscriminatorValue("C")
@Table(name = "Cliente")
public class Cliente extends UsuarioSGE  {

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
	@OneToOne(cascade = CascadeType.ALL)
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
		dispositivo.setInstanteDeCreacion(LocalDateTime.now());

	}

	public void removeDispositivo(Dispositivo dispositivo) {
		dispositivos.remove(dispositivo);

	}

	public Stream<Inteligente> misInteligentes() {
		List<Inteligente> list = new ArrayList<Inteligente>();
		dispositivos.stream().forEach((d)-> {
			if(d instanceof Inteligente)
			{
				list.add((Inteligente) d);
			}
        });

		return list.stream();
	}

	public Double ConsumoActualDispositivosInteligentes() {
		return this.misInteligentes().mapToDouble(dis -> ((Inteligente) dis).consumoActual()).sum();

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

	public Integer cantDispositivos() {
		return dispositivos.size();
	}

	public void presentate() {
		System.out.println("nombre:" + this.getNombre() + "  " + "apellido:" + this.getApellido() + "  "
				+ "FechaIngreso:" + this.getFechaIngreso() + "  " + "username:" + this.getUsername() + "  "
				+ "password:" + this.getPassword() + "\n" + "Domicilio:" + this.getDomicilio() + "  " + "Tipo Doc:"
				+ tipodoc + "  " + "Nro Doc:" + nrodoc + " " + "telefono:" + telefono + "  " + "Fecha de alta:"
				+ this.getFechaIngreso() + "\n" + "Dispositivos:" + "");
		this.presentarDispositivos();
	}

	public void presentarDispositivos() {

		dispositivos.forEach(d -> d.presentate());
	}

	public DispositivoConModulo agrega_modulo_a_estandar(DispositivoEstandar comun) {
		if (!dispositivos.contains(comun))
			throw new RuntimeException("solo se puede convertir dispositivos registrados");

		DispositivoConModulo conModulo = new DispositivoConModulo(comun, false, new DriverBasico());
		dispositivos.remove(comun);
		conModulo.setInstanteDeCreacion(LocalDateTime.now());
		dispositivos.add(conModulo);

		this.sumarPuntos(DispositivoConModulo.puntos());

		return conModulo;
	}

	private void sumarPuntos(Integer _puntos) {
		puntos += _puntos;
	}

	public Float consumo() {
		consumo= (float) dispositivos.stream().mapToDouble(dis -> dis.informarConsumo()).sum();
		return (float) dispositivos.stream().mapToDouble(dis -> dis.informarConsumo()).sum();
	}

	public Integer getPuntos() {
		return puntos;
	}

	public Double consumoEnPeriodo(LocalDateTime inicioPeriodo, LocalDateTime finPeriodo) {
		return dispositivos.stream().mapToDouble(dis -> dis.consumo_periodo(inicioPeriodo, finPeriodo)).sum();
	}

	public Recomendacion getMejorCombinacionDispositivos() {
		return new ModuloMejorCombinacion().calcularMejorCombinacion(this.dispositivos);
	}

	public boolean canYouGetMejorCombinacionDispositivos() {
		try {
			this.getMejorCombinacionDispositivos();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public void mejorarEficienciaHogar() {
		Recomendacion sugerencia = this.getMejorCombinacionDispositivos();
		this.misInteligentes().filter(i -> !sugerencia.esEficiente(i, this.consumoEnPeriodoDe(i))).forEach(i -> {
			accionParaMejorarEficiencia.ejecutar((Inteligente) i);
		});
	}

	public double consumoEnPeriodoDe(Dispositivo i) {
		LocalDateTime finPeriodo = LocalDateTime.now();
		LocalDateTime inicioPeriodo = finPeriodo.withDayOfMonth(1);
		return i.consumo_periodo(inicioPeriodo, finPeriodo);
	}
	public double miConsumoDelPeriodo() {
		LocalDateTime finPeriodo = LocalDateTime.now();
		LocalDateTime inicioPeriodo = finPeriodo.withDayOfMonth(1);
		return this.consumoEnPeriodo(inicioPeriodo, finPeriodo);
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

	public void llenarAtributos(Cliente c) {
		super.llenarAtributos((UsuarioSGE) c);
		setTipodoc(c.getTipoDoc());
		setNrodoc(c.getNrodoc());
		setTelefono(c.getTelefono());
		setCategoria(c.getCategoria());
		setDispositivos(c.getDispositivos());
		setPuntos(c.getPuntos());
		setUbicacion(c.getUbicacion());
		setAhorroAutomatico(c.isAhorroAutomatico());
		setAccionParaMejorarEficiencia(c.getAccionParaMejorarEficiencia());		
		
	}

}
