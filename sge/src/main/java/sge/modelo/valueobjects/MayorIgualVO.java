package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

public class MayorIgualVO extends ComparadorVO {
	public MayorIgualVO() {
		super();
		cmp = (v1, v2) -> v1 >= v2;
	}
}
