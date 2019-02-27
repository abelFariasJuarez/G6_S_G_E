package sge.modelo.regla;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.modelo.dispositivo.Inteligente;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
  @JsonSubTypes.Type(value=AccionAhorro.class, name = "AccionAhorro"),
  @JsonSubTypes.Type(value=AccionApagar.class, name = "AccionApagar"),
  @JsonSubTypes.Type(value=AccionPrender.class, name = "AccionPrender")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actionType")
@Table(name = "Accion")

public abstract class Accion extends Persistible {
	public Accion() {
		super();
	}

	public void ejecutar(Inteligente dispo) {

	}

}