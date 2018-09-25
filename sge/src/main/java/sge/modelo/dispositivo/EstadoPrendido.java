package sge.modelo.dispositivo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("P")
@Table(name="EstadoPrendido")
public class EstadoPrendido extends EstadoDispositivo {
	
	EstadoPrendido()
	{
		factor = 1;
	}

	@Override
	public void prender(Inteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		//no hacemos nada		
	}

	@Override
	public void apagar(Inteligente dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagado());		
	}

	@Override
	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergia());		
	}

}
