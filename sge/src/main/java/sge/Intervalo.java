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

	public long tiempoDesdeInstante(LocalDateTime instanteComienzo){
		LocalDateTime desde;
		if(inicio.compareTo(instanteComienzo)>=0) {
			desde = inicio;
		}
		else {
			desde = instanteComienzo;
		}
		Duration duration = Duration.between(desde, fin);
		return duration.getSeconds()/3600;
	}

	public double informarConsumo(DispositivoInteligente dispositivoInteligente,LocalDateTime instante) {
	
		long tiempo = this.tiempoDesdeInstante(instante);
		long factorEstado = estado.factor();
		return tiempo * dispositivoInteligente.getconsumoPorHora() * factorEstado;
	}
}
