package sge;

public class EstadoPrendido implements EstadoDispositivo {

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

	@Override
	public long factor() {
		// TODO Auto-generated method stub
		return 1;
	}

	

	

}
