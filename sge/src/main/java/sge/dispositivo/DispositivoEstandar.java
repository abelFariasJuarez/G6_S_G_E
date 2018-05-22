package sge.dispositivo;

import java.time.Duration;
import java.time.LocalDateTime;

import com.google.gson.annotations.SerializedName;

public class DispositivoEstandar extends Dispositivo {

	public DispositivoEstandar(String _nombre, Double _consumoPorHora,String _idUserName,Double _horasEncendido) {
		super(_nombre, _consumoPorHora,_idUserName);
		horasEncendido=_horasEncendido;
	}
	@SerializedName ("type")
	private String type;
	public DispositivoEstandar() {
	};

	private Double horasEncendido;

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
