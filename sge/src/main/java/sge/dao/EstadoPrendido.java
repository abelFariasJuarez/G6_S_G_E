package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.valueobjects.EstadoAhorroDeEnergiaVO;
import sge.modelo.valueobjects.EstadoApagadoVO;
import sge.modelo.valueobjects.EstadoDispositivoVO;

@Entity
@DiscriminatorValue("P")
@Table(name="EstadoPrendido")
public class EstadoPrendido extends EstadoDispositivo {
	
	EstadoPrendido()
	{
		factor = 1;
	}

}
