package sge.modelo.regla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.dispositivo.Inteligente;

@Entity
@DiscriminatorValue("PR")
@Table(name="AccionPrender")
public class AccionPrender extends Accion {	

	public void ejecutar(Inteligente dispo) {
		dispo.getDriver().prender();
	}
}
