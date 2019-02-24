package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

public class EstadoPrendidoVO extends EstadoDispositivoVO {
	
	EstadoPrendidoVO()
	{
		factor = 1;
	}

	@Override
	public void prender(InteligenteVO dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		//no hacemos nada		
	}

	@Override
	public void apagar(InteligenteVO dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagadoVO());		
	}

	@Override
	public void ahorroDeEnergia(InteligenteVO dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergiaVO());		
	}

}
