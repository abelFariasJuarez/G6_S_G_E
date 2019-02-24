package sge.modelo.valueobjects;

public class RestriccionHorasFamiliaVO {
	
	private String codigo;
	private double minimo;
	private double maximo;
  
	public RestriccionHorasFamiliaVO(String codigo, double minimo, double maximo) {
		super();
		this.codigo = codigo;
		this.minimo = minimo;
		this.maximo = maximo;
	}
	
	public RestriccionHorasFamiliaVO() {
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
