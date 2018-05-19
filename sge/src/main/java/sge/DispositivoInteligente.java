package sge;

import java.util.List;

public class DispositivoInteligente extends Dispositivo implements IInteligente {

	
	
	public DispositivoInteligente(String _nombre, Float _consumoPorHora, boolean _encendido) {
		super(_nombre, _consumoPorHora);
		encendido=_encendido;
		if(encendido==true) {
			this.setEstado(new EstadoPrendido());	
		}else {
			this.setEstado(new EstadoApagado());
		}

	}
	
	private boolean encendido;
	public List<Sensor> sensores;
	public EstadoDispositivo estado;
	
	@Override
	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
		
	}

	@Override
	public void setEstado(EstadoDispositivo _estado) {
		estado = _estado;		
	}

	@Override
	public boolean estoyON() {
		return encendido==true;
	}

	@Override
	public boolean estoyOFF() {
		return encendido==false;
	}

	@Override
	public void prender() {
		estado.prender(this);		
	}

	@Override
	public void apagar() {
		estado.apagar(this);		
	}

	@Override
	public void ahorroDeEnergia() {
		estado.ahorroDeEnergia(this);
		
	}

	
	@Override
	public void presentate() {
super.presentate();System.out.println("Encendido:"+encendido);
	}

	





	


}
