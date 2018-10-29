package sge.modelo.reporte;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import sge.modelo.reporte.Reporte;
import sge.modelo.dispositivo.*;

@Entity
@DiscriminatorValue("RD")
@Table(name = "ReporteDispositivo")

public class ReporteDispositivo extends Reporte {

	private Dispositivo dispositivo;
	
	public ReporteDispositivo() {
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
