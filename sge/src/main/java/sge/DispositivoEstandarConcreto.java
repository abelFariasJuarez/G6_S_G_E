package sge;

public class DispositivoEstandarConcreto extends DispositivoEstandar{

	public DispositivoEstandarConcreto(String _nombre, Float _consumoPorHora) {
		super(_nombre, _consumoPorHora);
		// TODO Auto-generated constructor stub
	}



	@Override
	public Float informarConsumo() {
		return this.getConsumoPorHora()*this.getHorasEncendido();
	}

}
