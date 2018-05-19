package sge;

import java.util.List;

public abstract class DispositivoConModulo extends DispositivoEstandar implements IInteligente {
	// decorator
	public DispositivoConModulo(DispositivoEstandar _dis, boolean _encendido) {
		dispo = _dis;
		encendido = _encendido;
		if (encendido) {
			this.setEstado(new EstadoPrendido());
		} else {
			this.setEstado(new EstadoApagado());
		}
	}

	private boolean encendido;
	public List<Sensor> sensores;
	public EstadoDispositivo estado;
	private DispositivoEstandar dispo;

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
		return encendido == true;
	}

	@Override
	public boolean estoyOFF() {
		return encendido == false;
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
	public Float informarConsumo() {
		// TODO Auto-generated method stub
		return null;
	}

	public DispositivoEstandar getDispo() {
		return dispo;
	}

	public void setDispo(DispositivoEstandar dispo) {
		this.dispo = dispo;
	}

}
