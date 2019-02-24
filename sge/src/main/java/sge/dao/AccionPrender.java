package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("PR")
@Table(name="AccionPrender")
public class AccionPrender extends Accion {	
}
