package sge.modelo.dispositivo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("A")
@Table(name="EstadoApagado")
public class EstadoApagado extends EstadoDispositivo {

	EstadoApagado() {
		factor = 0;
	}

	// dispositivo inteligente o con modulo
	@Override
	public void prender(Inteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendido());
	}

	@Override
	public void apagar(Inteligente dispositivoInteligente) {
		// no hacemos nada
	}

	@Override
	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
		super.ahorroDeEnergia(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergia());
	}
}
