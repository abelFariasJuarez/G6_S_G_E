package sge.modelo.regla.comparador;

import java.util.function.BiFunction;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.SerializedName;

import sge.modelo.Persistible;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "comparatorType")
@Table(name = "Comparador")
public abstract class Comparador extends Persistible{
	
	@Transient
	@SerializedName("type")
	private String typeName;
	@Transient 
	BiFunction<Double, Double, Boolean> cmp;
	
	public boolean comparar(double valorActual, double valorEsperado) {
		return cmp.apply(valorActual,valorEsperado);
	}

	public Comparador() {		
		super();
		typeName = getClass().getName();
	}
	
	
}
