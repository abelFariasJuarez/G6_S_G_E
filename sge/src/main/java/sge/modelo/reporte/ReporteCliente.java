package sge.modelo.reporte;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import sge.modelo.reporte.Reporte;
import sge.modelo.usuarios.Cliente;

@Entity
@DiscriminatorValue("RC")
@Table(name = "ReporteCliente")

public class ReporteCliente extends Reporte {

	private Cliente cliente;
	
	public ReporteCliente() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generarReporte() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarReporte() {
		// TODO Auto-generated method stub
		
	}

}
