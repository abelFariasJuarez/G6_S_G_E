package sge.modelo.valueobjects;

import java.time.Duration;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.dao.Persistible;

public class IntervaloVO {

	LocalDateTime inicio;
	LocalDateTime fin;
	EstadoDispositivoVO estado;

	public IntervaloVO() {
		super();
	}

	public double tiempoEnHorasValidoEntre(LocalDateTime instanteComienzo, LocalDateTime instanteFin) {
		LocalDateTime desde = inicio;
		LocalDateTime hasta = fin;

		if (inicio.isBefore(instanteComienzo)) {
			desde = instanteComienzo;
		}

		if (fin == null || fin.isAfter(instanteFin)) {
			hasta = instanteFin;
		}

		Duration duration = Duration.between(desde, hasta);
		return duration.getSeconds() / 3600.0;
	}

	public double informarConsumo(InteligenteVO dispositivoInteligente, LocalDateTime instante,
			LocalDateTime instanteHasta) {

		double tiempo = this.tiempoEnHorasValidoEntre(instante, instanteHasta);
		return tiempo * estado.consumoFinal(dispositivoInteligente.getConsumoPorHora());
	}

	public boolean estoyDentroDePeriodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {

		return this.mi_inicio_es_despues_o_igual_a(instanteDesde)
				&& this.mi_inicio_es_antes_o_igual_a(instanteHasta)
				&& this.mi_fin_es_antes_o_igual_a(instanteHasta);
	}

	public boolean periodoEstaDentroDeMi(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return this.mi_inicio_es_antes_o_igual_a(instanteDesde) && this.mi_fin_es_despues_o_igual_a(instanteHasta);
	}

	private boolean mi_fin_es_despues_o_igual_a(LocalDateTime instanteHasta) {
		return fin == null || instanteHasta.isBefore(fin) || instanteHasta.isEqual(fin);
	}

	private boolean mi_inicio_es_antes_o_igual_a(LocalDateTime instanteDesde) {
		return inicio == null || instanteDesde.isAfter(inicio) || instanteDesde.isEqual(inicio);
	}

	private boolean mi_inicio_es_despues_o_igual_a(LocalDateTime instanteDesde) {
		return instanteDesde.isBefore(inicio) || instanteDesde.isEqual(inicio);
	}

	private boolean mi_fin_es_antes_o_igual_a(LocalDateTime instanteHasta) {
		return fin == null || instanteHasta.isAfter(fin) || instanteHasta.isEqual(fin);
	}

	public void setInicio(LocalDateTime ini) {
		inicio = ini;
	}

	public void setFin(LocalDateTime now) {
		fin = now;
	}

	public void setEstado(EstadoDispositivoVO _estado) {
		estado = _estado;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public EstadoDispositivoVO getEstado() {
		return estado;
	}

	public boolean estoyEncendido() {
		return this.getEstado() instanceof EstadoPrendidoVO;
	}

	public void presentate() {
		System.out.println("\testado: " + this.getEstado().toString() + "\tinicio: " + this.getInicio() + "\tfin: "
				+ this.getFin());
	}

}
