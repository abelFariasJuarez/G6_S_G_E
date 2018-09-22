package sge.modelo.driver;

import java.util.ArrayList;
import java.util.List;

import sge.modelo.dispositivo.DispositivoInteligente;
import sge.modelo.regla.Regla;

public class ReglasMasDispositivos {
	public List<Regla> reglas=new ArrayList<Regla>();;
	public DispositivoInteligente dispositivo;
	
	
	
	
	public ReglasMasDispositivos(List<Regla> _reglas, DispositivoInteligente _dispositivo) {
		super();
		reglas = _reglas;
		dispositivo = _dispositivo;
	}
	
	
	public List<Regla> getReglas() {
		return reglas;
	}
	public void setReglas(List<Regla> _reglas) {
		reglas = _reglas;
	}
	public DispositivoInteligente getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(DispositivoInteligente _dispositivo) {
		dispositivo = _dispositivo;
	}
	
	
}
