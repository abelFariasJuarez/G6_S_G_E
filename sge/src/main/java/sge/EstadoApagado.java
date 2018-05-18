package sge;

public class EstadoApagado implements EstadoDispositivo {

	@Override
	public void prender(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoPrendido());
		dispositivoInteligente.setEncendido(true);
		
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		
	}



	

	


	
	
	

}
