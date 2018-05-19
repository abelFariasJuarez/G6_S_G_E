package sge;

public class EstadoApagado extends EstadoDispositivo {

	EstadoApagado()
	{
		factor = 0;
	}
	// dispositivo inteligente o con modulo
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
