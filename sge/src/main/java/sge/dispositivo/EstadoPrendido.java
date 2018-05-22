package sge.dispositivo;

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
