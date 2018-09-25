package sge.modelo.dispositivo;

import java.time.Duration;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Table(name = "Intervalo")
public class Intervalo extends Persistible {

	@Column(name = "inicio")
	LocalDateTime inicio;
	@Column(name = "fin")
	LocalDateTime fin;
	@OneToOne(cascade = CascadeType.ALL)
	EstadoDispositivo estado;
	
	public Intervalo() {
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

	public double informarConsumo(Inteligente dispositivoInteligente, LocalDateTime instante,
			LocalDateTime instanteHasta) {

		double tiempo = this.tiempoEnHorasValidoEntre(instante, instanteHasta);
		return tiempo * estado.consumoFinal(dispositivoInteligente.getConsumoPorHora());
	}

	public boolean estoyDentroDePeriodo(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {

		return this.mi_inicio_es_despues_o_igual_a(instanteDesde) &&
				this.mi_fin_es_antes_o_igual_a(instanteHasta);
	}

	public boolean periodoEstaDentroDeMi(LocalDateTime instanteDesde, LocalDateTime instanteHasta) {
		return this.mi_inicio_es_antes_o_igual_a(instanteDesde) &&
				this.mi_fin_es_despues_o_igual_a(instanteHasta);
	}

	private boolean mi_fin_es_despues_o_igual_a(LocalDateTime instanteHasta) {
		return fin == null ||instanteHasta.isBefore(fin) || instanteHasta.isEqual(fin);
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

	public void setEstado(EstadoDispositivo _estado) {
		estado = _estado;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public EstadoDispositivo getEstado() {
		return estado;
	}
	
	
}
