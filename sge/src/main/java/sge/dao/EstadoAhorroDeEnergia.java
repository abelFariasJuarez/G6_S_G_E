package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.EstadoDispositivoVO;

@Entity
@DiscriminatorValue("E")
@Table(name="EstadoAhorroDeEnergia")
public class EstadoAhorroDeEnergia extends EstadoDispositivo {

	EstadoAhorroDeEnergia() {
		factor = 0.7;//Agregar a tabla de decisiones 
	}
}
