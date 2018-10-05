package sge.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Persistible implements Serializable, IPersistible{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long oid;

	protected Persistible() {
	}

	@Column(name = "oid")
	public Long getOid() {
		return oid;
	}

	public void setOid(Long id) {
		this.oid = id;
	}

	public String toString() {
		return getOid() + "-" + this.getClass().getSimpleName();
	}
}