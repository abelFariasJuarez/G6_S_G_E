package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.EstadoAhorroDeEnergiaVO;
import sge.modelo.valueobjects.EstadoDispositivoVO;

@Entity
@DiscriminatorValue("A")
@Table(name="EstadoApagado")
public class EstadoApagado extends EstadoDispositivo {

	EstadoApagado() {
		factor = 0;
	}
}
