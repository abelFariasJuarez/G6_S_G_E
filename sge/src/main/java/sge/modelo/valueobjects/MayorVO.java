package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

public class MayorVO extends ComparadorVO {

	public MayorVO() {
		super();
		cmp = (v1, v2) -> v1 > v2;
	}
	
}
