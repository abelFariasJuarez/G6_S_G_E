package sge.dao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("AP")
@Table(name="AccionApagar")
public class AccionApagar extends Accion {
}
