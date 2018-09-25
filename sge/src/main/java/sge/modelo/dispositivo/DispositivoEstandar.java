package sge.modelo.dispositivo;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

@Entity
@DiscriminatorValue("E")
@Table(name="DispositivoEstandar")
public class DispositivoEstandar extends Dispositivo {

	@SerializedName ("type")
	@Transient
	private String type;

	@Column(name = "horasEncendido")	
	private Double horasEncendido;

	public DispositivoEstandar() {
	}

	public DispositivoEstandar(String _nombre, Double _consumoPorHora,String _idUserName,Boolean _bajoconsumo,Double _horasEncendido) {
		super(_nombre, _consumoPorHora,_idUserName, _bajoconsumo);
		horasEncendido=_horasEncendido;
	}
	
	public DispositivoEstandar(String _nombre, Double _consumoPorHora,Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		horasEncendido=0.0;
	}

	public Double getHorasEncendido() {
		return horasEncendido;
	}

	public void setHorasEncendido(Double _horasEncendido) {
		horasEncendido = _horasEncendido;
	}

	@Override
	public Double informarConsumo() {
		return this.getConsumoPorHora() * this.getHorasEncendido();
	}
	
	public Double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		Duration duration = Duration.between(instanteDesde, instanteHasta);
		return consumoPorHora * horasEncendido * (duration.getSeconds() / 3600.0 / 24.0) ;
	}

}
