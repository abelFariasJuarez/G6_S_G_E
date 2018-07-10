package sge.dispositivo.familia;

import sge.dispositivo.DispositivoEstandar;

public class Microondas extends DispositivoEstandar {

	public Microondas(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		this.restriccionHoras(RestriccionHorasFamilia.MICROWAVE);
	}

}
