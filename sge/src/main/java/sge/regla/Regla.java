package sge.regla;


import java.util.ArrayList;
import java.util.List;


public class Regla implements CambiaPorElSensor{


	private String name;
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	private List<Accion> actuadores = new ArrayList<Accion>();

	public Regla(String _name) {

		name = _name;

	}
	public void update(){
		this.accionarSiCorresponde();
	}
	
	public void accionarSiCorresponde() {
		if (this.verificarCondiciones()) {
			this.ejecutarAcciones();
			
		}
		
		
		
	}

	
	
	private void ejecutarAcciones() {
		actuadores.forEach(a -> a.ejecutarAccion());

	}

	
	
	private boolean verificarCondiciones() {
		return condiciones.stream().allMatch(cond -> cond.meCumplo());

	}

	
	
	
	
	public void agregarCondicion(Condicion unaCondicion) {
		condiciones.add(unaCondicion);
	}

	public void agregarActuador(Actuador unActuador) {
		actuadores.add(unActuador);
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		name = _name;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> _condiciones) {
		condiciones = _condiciones;
	}

	public List<Actuador> getActuadores() {
		return actuadores;
	}

	public void setActuadores(List<Actuador> _actuadores) {
		actuadores = _actuadores;
	}
}


