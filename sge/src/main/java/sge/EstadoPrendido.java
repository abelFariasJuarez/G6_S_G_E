package sge;

public class EstadoPrendido extends EstadoDispositivo {
	
	EstadoPrendido()
	{
		factor = 1;
	}

	@Override
	public void prender(IInteligente dispositivoInteligente) {		
		
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoApagado());
		dispositivoInteligente.setEncendido(false);
		
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergia());		
	}

}
