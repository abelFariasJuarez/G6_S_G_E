package sge.dispositivo;

public abstract class  DispositivoEstandar extends Dispositivo {

	
	
	public DispositivoEstandar(String _nombre, Double _consumoPorHora) {
		super(_nombre, _consumoPorHora);
		
		
	}
	public DispositivoEstandar() {
	};

	private Float horasEncendido;

	
	public Float getHorasEncendido() {
		return horasEncendido;
	}
	public void setHorasEncendido(Float _horasEncendido) {
		horasEncendido = _horasEncendido;
	}

	
	public abstract Double informarConsumo();
}
