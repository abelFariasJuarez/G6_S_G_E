package sge;

public class EstadoAhorroDeEnergia implements EstadoDispositivo {

	@Override
	public void prender(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEstado(new EstadoPrendido());
		
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		// TODO Auto-generated method stub
		
	}



}
