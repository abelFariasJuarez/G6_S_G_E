package sge;

public class EstadoAhorroDeEnergia extends EstadoDispositivo {

	EstadoAhorroDeEnergia() {
		factor = 0.7;
	}

	@Override
	public void prender(IInteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendido());
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagado());
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		// No hacemos nada
	}

}
