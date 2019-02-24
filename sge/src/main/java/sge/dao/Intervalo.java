package sge.dao;

import java.time.Duration;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import sge.dao.Persistible;
import sge.modelo.valueobjects.EstadoDispositivoVO;
import sge.modelo.valueobjects.EstadoPrendidoVO;


@Entity
@Table(name = "Intervalo")
public class Intervalo extends Persistible {

	@Column(name = "inicio")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime inicio;
	@Column(name = "fin")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime fin;
	@OneToOne(cascade = CascadeType.ALL)
	EstadoDispositivo estado;

	public Intervalo() {
		super();
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

	public void presentate() {
		System.out.println("\testado: " + this.getEstado().toString() + "\tinicio: " + this.getInicio() + "\tfin: "
				+ this.getFin());
	}

}
