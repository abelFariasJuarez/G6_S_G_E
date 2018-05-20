package sge.dispositivo;

public class EstadoPrendido extends EstadoDispositivo {
	
	EstadoPrendido()
	{
		factor = 1;
	}

	@Override
	public void prender(IInteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		//no hacemos nada		
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagado());		
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergia());		
	}

}
