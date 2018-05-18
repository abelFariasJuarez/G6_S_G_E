package sge;

public abstract class  DispositivoEstandar extends Dispositivo {

	
	
	public DispositivoEstandar(String _nombre, Float _consumoPorHora) {
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

	
	public abstract Float informarConsumo();
}
