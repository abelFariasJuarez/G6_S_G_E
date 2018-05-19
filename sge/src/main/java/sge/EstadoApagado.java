package sge;

public class EstadoApagado implements EstadoDispositivo {

	//dispositivo inteligente o con modulo
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

	@Override
	public long factor() {
		// TODO Auto-generated method stub
		return 0;
	}



	

	


	
	
	

}
