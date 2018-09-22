package sge.modelo.dispositivo;

import java.time.Duration;
import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public class DispositivoEstandar extends Dispositivo {

	@SerializedName ("type")
	private String type;
	private Double horasEncendido;

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
