package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

public class EstadoAhorroDeEnergiaVO extends EstadoDispositivoVO {

	EstadoAhorroDeEnergiaVO() {
		factor = 0.7;//Agregar a tabla de decisiones 
	}

	@Override
	public void prender(InteligenteVO dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendidoVO());
	}

	@Override
	public void apagar(InteligenteVO dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagadoVO());
	}

	@Override
	public void ahorroDeEnergia(InteligenteVO dispositivoInteligente) {
		// No hacemos nada
	}

}
