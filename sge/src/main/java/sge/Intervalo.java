package sge;

import java.time.Duration;

import java.time.LocalDateTime;

public class Intervalo {
	LocalDateTime inicio;
	LocalDateTime fin;
	EstadoDispositivo estado;

	public boolean pertenece(LocalDateTime instanteComienzo) {
		return fin.compareTo(instanteComienzo) >= 0;

	}

	public double tiempoEnHorasValidoEntre(LocalDateTime instanteComienzo, LocalDateTime instanteFin) {
		LocalDateTime desde = inicio;
		LocalDateTime hasta = fin;

		if (inicio.isBefore(instanteComienzo)) {
			desde = instanteComienzo;
		}

		if (fin.isAfter(instanteFin)) {
			hasta = instanteFin;
		}

		Duration duration = Duration.between(desde, hasta);
		return duration.getSeconds() / 3600.0;
	}

	public double informarConsumo(DispositivoInteligente dispositivoInteligente, LocalDateTime instante,
			LocalDateTime instanteHasta) {

		double tiempo = this.tiempoEnHorasValidoEntre(instante, instanteHasta);
		return tiempo * estado.consumoFinal(dispositivoInteligente.getConsumoPorHora());
	}

	public boolean dentroDePeriodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {

		return (instanteDesde.isBefore(inicio) || instanteDesde.isEqual(inicio))
				&& (instanteHasta.isAfter(fin) || instanteHasta.isEqual(fin));
	}

	public void setInicio(LocalDateTime ini) {
		inicio = ini;
	}

	public void setFin(LocalDateTime now) {
		fin = now;
	}

	public void setEstado(EstadoDispositivo _estado) {
		estado = _estado;
	}
}
