package sge.modelo.valueobjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
@Table(name="EstadoApagado")
public class EstadoApagadoVO extends EstadoDispositivoVO {

	EstadoApagadoVO() {
		factor = 0;
	}

	// dispositivo inteligente o con modulo
	@Override
	public void prender(InteligenteVO dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendidoVO());
	}

	@Override
	public void apagar(InteligenteVO dispositivoInteligente) {
		// no hacemos nada
	}

	@Override
	public void ahorroDeEnergia(InteligenteVO dispositivoInteligente) {
		super.ahorroDeEnergia(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergiaVO());
	}
}
