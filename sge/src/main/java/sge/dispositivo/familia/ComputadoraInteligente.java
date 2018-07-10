package sge.dispositivo.familia;

import sge.dispositivo.DispositivoInteligente;

public class ComputadoraInteligente extends DispositivoInteligente {

	public ComputadoraInteligente(String _nombre, Double _consumoPorHora, Boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		this.restriccionHoras(RestriccionHorasFamilia.COMPUTER);
	}



	

}
