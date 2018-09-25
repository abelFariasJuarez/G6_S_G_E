package sge.modelo.dispositivo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.driver.DriverBasico;

@Entity
@DiscriminatorValue("M")
@Table(name="DispositivoConModulo")
public class DispositivoConModulo extends Inteligente {
	@Transient
	private static Integer puntos = 10;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DispositivoEstandar dispo;
	
	public DispositivoConModulo() {
		super();
	}
	
	public DispositivoConModulo(DispositivoEstandar _dis,DriverBasico driver) {
		
		super("", 0.0, _dis.bajoconsumo,driver);
		// TODO Auto-generated constructor stub
		dispo = _dis;
	}
	
	// decorator|adapter
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido,String _idUserName,DriverBasico driver) {
		
		super("", 0.0,"",_dis.bajoconsumo, _encendido,driver);
		dispo = _dis;
	}
	
	
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido,DriverBasico driver) {
		super("", 0.0,"",_dis.bajoconsumo, _encendido,driver);
		dispo = _dis;
	}

	@Override
	public String getNombre() {
		return dispo.getNombre();
	}

	@Override
	public Double getConsumoPorHora() {
		return dispo.getConsumoPorHora();
	}

	public void presentate() {
		System.out.println("\t" + this.getNombre() + " " + this.getConsumoPorHora() + "  ");
	}

	public DispositivoEstandar getEstandar() {
		return dispo;
	}

	public void setEstandar(DispositivoEstandar dispo) {
		this.dispo = dispo;
	}

	public static Integer puntos() {
		return puntos;
	}

	public Double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return super.consumo_periodo(instanteDeCreacion, instanteHasta) + dispo.consumo_periodo(instanteDesde, instanteDeCreacion);
	}
	
	@Override
	public RestriccionHorasFamilia getRestriccionHoras() {
		return dispo.getRestriccionHoras();
	}
	
	@Override
	public void setRestriccionHoras(RestriccionHorasFamilia rhf) {
		dispo.setRestriccionHoras(rhf);
	}
	
	@Override
	public double mensualMinimoHoras()
	{
		return dispo.mensualMinimoHoras();
	}
	
	@Override
	public double mensualMaximoHoras()
	{
		return dispo.mensualMaximoHoras();
	}


}
