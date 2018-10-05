package sge.modelo.regla;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.dispositivo.Inteligente;

@Entity
@DiscriminatorValue("AP")
@Table(name="AccionApagar")
public class AccionApagar extends Accion {
	public void ejecutar(Inteligente dispo) {
		dispo.getDriver().apagar();
	}
}
