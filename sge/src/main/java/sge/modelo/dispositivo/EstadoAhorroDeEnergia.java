package sge.modelo.dispositivo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("E")
@Table(name="EstadoAhorroDeEnergia")
public class EstadoAhorroDeEnergia extends EstadoDispositivo {

	EstadoAhorroDeEnergia() {
		factor = 0.7;//Agregar a tabla de decisiones 
	}

	@Override
	public void prender(Inteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendido());
	}

	@Override
	public void apagar(Inteligente dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagado());
	}

	@Override
	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
		// No hacemos nada
	}

}
