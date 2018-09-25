package sge.modelo.dispositivo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.Persistible;

@Entity
@Table(name = "RestriccionHorasFamilia")
public class RestriccionHorasFamilia extends Persistible{
	
	@Column(name = "codigo")
	private String codigo;
	@Column(name = "minimo")
	private double minimo;
	@Column(name = "maximo")
	private double maximo;
  

	public RestriccionHorasFamilia(String codigo, double minimo, double maximo) {
		super();
		this.codigo = codigo;
		this.minimo = minimo;
		this.maximo = maximo;
	}
	
	public RestriccionHorasFamilia() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public double getMinimo() {
		return minimo;
	}
	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}
	public double getMaximo() {
		return maximo;
	}
	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}  

}
