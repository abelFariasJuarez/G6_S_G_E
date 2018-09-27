package sge.modelo.regla;

import java.util.ArrayList;
import java.util.List;

import sge.modelo.dispositivo.Inteligente;

public class Regla {

	private String name;
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	private List<Accion> acciones = new ArrayList<Accion>();

	public Regla(String _name) {
		name = _name;
	}

	public void accionarSiCorresponde(Inteligente dispo) {
		if (this.verificarCondiciones()) {
			this.ejecutarAcciones(dispo);
		}

	}

	private void ejecutarAcciones(Inteligente dispo) {
		acciones.forEach(a -> a.ejecutar(dispo));
	}

	private boolean verificarCondiciones() {
		return condiciones.stream().allMatch(cond -> cond.meCumplo());
	}

	public void agregarCondicion(Condicion unaCondicion) {
		condiciones.add(unaCondicion);
	}

	public void agregarAccion(Accion unaAccion) {
		acciones.add(unaAccion);
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

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> _actuadores) {
		acciones = _actuadores;
	}

}
