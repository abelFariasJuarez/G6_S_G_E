package sge.dispositivo;

public abstract class EstadoDispositivo {

	double factor;

	public void prender(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public void apagar(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(false);
	}

	public void ahorroDeEnergia(IInteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public double factor() {
		return factor;
	}

	public double consumoFinal(double consumoPorHora) {
		double value = factor * consumoPorHora;
		return value;
	}

}
