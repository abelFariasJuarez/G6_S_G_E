package sge;

public abstract class EstadoDispositivo {

	double factor;

	public abstract void prender(IInteligente dispositivoInteligente);

	public abstract void apagar(IInteligente dispositivoInteligente);

	public abstract void ahorroDeEnergia(IInteligente dispositivoInteligente);

	public double factor() {
		return factor;
	}

	public double consumoFinal(Double consumoPorHora) {
		return factor * consumoPorHora;
	}

}
