package sge.modelo.regla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.dispositivo.Inteligente;

@Entity
@DiscriminatorValue("AH")
@Table(name = "AccionAhorrro")
public class AccionAhorrro extends Accion {
	
	
	public void ejecutar(Inteligente dispo) {
		dispo.getDriver().ahorroDeEnergia();
	}
}
