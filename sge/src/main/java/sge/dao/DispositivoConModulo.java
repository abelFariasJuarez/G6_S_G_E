package sge.dao;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import sge.modelo.valueobjects.RestriccionHorasFamiliaVO;

@Entity
@DiscriminatorValue("M")
@Table(name="DispositivoConModulo")
public class DispositivoConModulo extends Inteligente {
	
	@OneToOne(cascade = CascadeType.ALL)
	private DispositivoEstandar dispo;
	
	public DispositivoConModulo() {
		super();
	}
	
	public DispositivoConModulo(DispositivoEstandar _dis) {
		
		super("", 0.0, _dis.bajoconsumo);	
		dispo = _dis;
	}
	
	// decorator|adapter
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido,String _idUserName) {
		
		super("", 0.0,"",_dis.bajoconsumo, _encendido);
		dispo = _dis;
	}
	
	
	public DispositivoConModulo(DispositivoEstandar _dis, Boolean _encendido) {
		super("", 0.0,"",_dis.bajoconsumo, _encendido);
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

	@Override
	public RestriccionHorasFamiliaVO getRestriccionHoras() {
		return dispo.getRestriccionHoras();
	}
	
	@Override
	public void setRestriccionHoras(RestriccionHorasFamiliaVO rhf) {
		dispo.setRestriccionHoras(rhf);
	}

}
