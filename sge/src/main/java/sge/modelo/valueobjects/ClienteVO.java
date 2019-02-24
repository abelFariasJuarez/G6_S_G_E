package sge.modelo.valueobjects;

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

import sge.modelo.DriverBasico;
import sge.modelo.ModuloMejorCombinacion;
import sge.modelo.Recomendacion;

public class ClienteVO extends UsuarioVO  {

	private String tipodoc;
	private Integer nrodoc;
	private Integer telefono;
	private CategoriaVO categoria;
	private List<DispositivoVO> dispositivos = new ArrayList<DispositivoVO>();
	private Integer puntos = 0;
	private UbicacionVO ubicacion;
	private boolean ahorroAutomatico = false; // o accion automatica o accione por si solo
	private AccionVO accionParaMejorarEficiencia = new AccionApagarVO();// la orden de "apagar" (podria ser accion
	
	private  float consumo;

	public ClienteVO() {
	}

	public ClienteVO(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;

	}

	public ClienteVO(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, UbicacionVO _ubi) {
		super(_nombre, _apellido, _domicilio, _fechaIngreso, _username, _password);
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		ubicacion = _ubi;

	}

	public ClienteVO(String _nombre, String _apellido, String _domicilio, LocalDate _fechaIngreso, String _username,
			String _password, String _tipodoc, Integer _nrodoc, Integer _telefono, List<DispositivoVO> _dispositivos,
			UbicacionVO _ubi) {
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

	public CategoriaVO getCategoria() {

		return categoria;
	}

	public void addDispositivo(DispositivoVO dispositivo) {
		dispositivos.add(dispositivo);
		dispositivo.setInstanteDeCreacion(LocalDateTime.now());
	}

	public void removeDispositivo(DispositivoVO dispositivo) {
		dispositivos.remove(dispositivo);
	}

	public Stream<DispositivoVO> misInteligentes() {
		return dispositivos.stream().filter(dis -> dis instanceof InteligenteVO);
	}

	public Double ConsumoActualDispositivosInteligentes() {
		return this.misInteligentes().mapToDouble(dis -> ((InteligenteVO) dis).consumoActual()).sum();

	}

	public boolean tengoAlgunDispositivoON() {
		return this.misInteligentes().anyMatch(dis -> ((InteligenteVO) dis).estoyON());
	}

	public Integer cantDispositivosON() {
		return (int) this.misInteligentes().filter(dis -> ((InteligenteVO) dis).estoyON()).count();
	}

	public Integer cantDispositivosOFF() {
		return (int) this.misInteligentes().filter(dis -> ((InteligenteVO) dis).estoyOFF()).count();
	}

	public List<DispositivoVO> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoVO> dispositivos) {
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

	public DispositivoConModuloVO agrega_modulo_a_estandar(DispositivoEstandarVO comun) {
		if (!dispositivos.contains(comun))
			throw new RuntimeException("solo se puede convertir dispositivos registrados");

		DispositivoConModuloVO conModulo = new DispositivoConModuloVO(comun, false, new DriverBasico());
		dispositivos.remove(comun);
		conModulo.setInstanteDeCreacion(LocalDateTime.now());
		dispositivos.add(conModulo);

		this.sumarPuntos(DispositivoConModuloVO.puntos());

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
			accionParaMejorarEficiencia.ejecutar((InteligenteVO) i);
		});
	}

	public double consumoEnPeriodoDe(DispositivoVO i) {
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

	public UbicacionVO getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionVO ubicacion) {
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

	public AccionVO getAccionParaMejorarEficiencia() {
		return accionParaMejorarEficiencia;
	}

	public void setAccionParaMejorarEficiencia(AccionVO accionParaMejorarEficiencia) {
		this.accionParaMejorarEficiencia = accionParaMejorarEficiencia;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public void setCategoria(CategoriaVO categoria) {
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
