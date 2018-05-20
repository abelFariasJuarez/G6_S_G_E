package sge.dispositivo;

public class DispositivoEstandar extends Dispositivo {

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

	@Override
	public Double informarConsumo() {
		return this.getConsumoPorHora() * this.getHorasEncendido();
	}

}
