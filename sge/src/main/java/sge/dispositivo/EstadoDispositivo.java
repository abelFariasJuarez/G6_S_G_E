package sge.dispositivo;

public abstract class EstadoDispositivo {

	double factor;

	public void prender(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(true);
	}

	public void apagar(Inteligente dispositivoInteligente) {
		dispositivoInteligente.setEncendido(false);
	}

	public void ahorroDeEnergia(Inteligente dispositivoInteligente) {
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
