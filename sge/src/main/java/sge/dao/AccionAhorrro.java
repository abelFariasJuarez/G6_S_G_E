package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("AH")
@Table(name = "AccionAhorrro")
public class AccionAhorrro extends Accion {
	
}
