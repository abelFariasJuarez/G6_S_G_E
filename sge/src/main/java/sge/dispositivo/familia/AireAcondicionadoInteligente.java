package sge.dispositivo.familia;

import sge.dispositivo.DispositivoInteligente;
import sge.dispositivo.Inteligente;

public class AireAcondicionadoInteligente extends DispositivoInteligente {

	public AireAcondicionadoInteligente(String _nombre, Double _consumoPorHora, boolean _bajoconsumo) {
		super(_nombre, _consumoPorHora, _bajoconsumo);
		this.restriccionHoras(RestriccionHorasFamilia.AIRCONDITIONER);
	}
}
