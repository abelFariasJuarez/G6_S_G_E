package sge.modelo.valueobjects;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

public class DispositivoEstandarVO extends DispositivoVO {

	private String type;

	private Double horasEncendidoPorDia;

	public DispositivoEstandarVO() {
	}

	public DispositivoEstandarVO(String _nombre, Double _consumoPorHora,String _idUserName,Boolean _bajoconsumo,Double _horasEncendido) {
		super(_nombre, _consumoPorHora,_idUserName, _bajoconsumo);
		horasEncendidoPorDia=_horasEncendido;
	}
	
	public DispositivoEstandarVO(String _nombre, Double _consumoPorHora,Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		horasEncendidoPorDia=0.0;
	}

	public Double getHorasEncendidoPorDia() {
		return horasEncendidoPorDia;
	}

	public void setHorasEncendidoPorDia(Double _horasEncendido) {
		horasEncendidoPorDia = _horasEncendido;
	}

	@Override
	public Double informarConsumo() {
		return this.getConsumoPorHora() * this.getHorasEncendidoPorDia();
	}
	
	public Double consumo_periodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		Duration duration = Duration.between(instanteDesde, instanteHasta);
		return consumoPorHora * horasEncendidoPorDia * (duration.getSeconds() / 3600.0 / 24.0) ;
	}
	
	public String getNombreEstado() {
		return "Indefinido";
	}
	
	public List<ReglaVO> getReglas() {
		return new ArrayList<ReglaVO>();
	}

}
