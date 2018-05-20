package sge.dispositivo;

public class EstadoApagado extends EstadoDispositivo {

	EstadoApagado() {
		factor = 0;
	}

	// dispositivo inteligente o con modulo
	@Override
	public void prender(IInteligente dispositivoInteligente) {
		super.prender(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoPrendido());
	}

	@Override
	public void apagar(IInteligente dispositivoInteligente) {
		// no hacemos nada
	}

	@Override
	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		super.ahorroDeEnergia(dispositivoInteligente);
		dispositivoInteligente.setEstado(new EstadoAhorroDeEnergia());
	}
}
