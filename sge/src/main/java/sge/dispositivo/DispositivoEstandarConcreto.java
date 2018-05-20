package sge.dispositivo;

public class DispositivoEstandarConcreto extends DispositivoEstandar{

	public DispositivoEstandarConcreto(String _nombre, Double _consumoPorHora) {
		super(_nombre, _consumoPorHora);
		// TODO Auto-generated constructor stub
	}



	@Override
	public Double informarConsumo() {
		return this.getConsumoPorHora()*this.getHorasEncendido();
	}

}
