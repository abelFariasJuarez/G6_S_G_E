package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

public class MenorVO extends ComparadorVO {
	
	public MenorVO() {
		super();
		cmp = (v1, v2) -> v1 < v2;
	}
	
}
