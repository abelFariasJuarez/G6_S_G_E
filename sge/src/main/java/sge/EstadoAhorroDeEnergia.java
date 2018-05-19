package sge;

public class EstadoAhorroDeEnergia extends EstadoDispositivo {

	EstadoAhorroDeEnergia() {
		factor = 0.7;
	}

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
