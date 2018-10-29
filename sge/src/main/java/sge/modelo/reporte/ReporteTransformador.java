package sge.modelo.reporte;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import sge.modelo.reporte.Reporte;
import sge.modelo.posicionamiento.Transformador;

@Entity
@DiscriminatorValue("RT")
@Table(name = "ReporteTransformador")

public class ReporteTransformador extends Reporte {

	private Transformador transformador;
	
	public ReporteTransformador() {
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

