package sge.dao;

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

import sge.modelo.valueobjects.DispositivoVO;

@Entity
@DiscriminatorValue("E")
@Table(name="DispositivoEstandar")
public class DispositivoEstandar extends Dispositivo {

	@SerializedName ("type")
	@Transient
	private String type;

	@Column(name = "horasEncendidoPorDia")	
	private Double horasEncendidoPorDia;

	public DispositivoEstandar() {
	}

	public DispositivoEstandar(String _nombre, Double _consumoPorHora,String _idUserName,Boolean _bajoconsumo,Double _horasEncendido) {
		super(_nombre, _consumoPorHora,_idUserName, _bajoconsumo);
		horasEncendidoPorDia=_horasEncendido;
	}
	
	public DispositivoEstandar(String _nombre, Double _consumoPorHora,Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		horasEncendidoPorDia=0.0;
	}

	public Double getHorasEncendidoPorDia() {
		return horasEncendidoPorDia;
	}

	public void setHorasEncendidoPorDia(Double _horasEncendido) {
		horasEncendidoPorDia = _horasEncendido;
	}
	
	public String getNombreEstado() {
		return "Indefinido";
	}
	
	public List<Regla> getReglas() {
		return new ArrayList<Regla>();
	}

}
