package sge.dispositivo;

public interface IInteligente {
	public void setEncendido(boolean encendido);
	public void setEstado(EstadoDispositivo _estado);
	public boolean estoyON();
	public boolean estoyOFF();
	public void prender();
	public void apagar();
	public void ahorroDeEnergia();
}
