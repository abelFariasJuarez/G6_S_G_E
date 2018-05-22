package sge.dispositivo;

public class EstadoAhorroDeEnergia extends EstadoDispositivo {

	EstadoAhorroDeEnergia() {
		factor = 0.7;
	}

	@Override
	public void prender(Inteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendido());
	}

	@Override
	public void apagar(Inteligente dispositivoInteligente) {
		super.apagar(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoApagado());
	}

	@Override
	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
		// No hacemos nada
	}

}
