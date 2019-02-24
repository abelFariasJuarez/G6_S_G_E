package sge.modelo.valueobjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.dao.Persistible;

public class ReglaVO {

	private String name;
	private List<CondicionVO> condiciones = new ArrayList<CondicionVO>();
	private List<AccionVO> acciones = new ArrayList<AccionVO>();
	
	/*@ManyToOne(cascade=CascadeType.ALL)
	private Inteligente inteligente;

	public Inteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(Inteligente dispositivo) {
		this.inteligente = dispositivo;
	}*/

	public ReglaVO() {
	}
	
	public ReglaVO(String _name) {
		name = _name;
	}

	public void accionarSiCorresponde(InteligenteVO dispo) {
		if (this.verificarCondiciones()) {
			this.ejecutarAcciones(dispo);
		}

	}

	private void ejecutarAcciones(InteligenteVO dispo) {
		acciones.forEach(a -> a.ejecutar(dispo));
	}

	private boolean verificarCondiciones() {
		return condiciones.stream().allMatch(cond -> cond.meCumplo());
	}

	public void agregarCondicion(CondicionVO unaCondicion) {
		condiciones.add(unaCondicion);
	}

	public void agregarAccion(AccionVO unaAccion) {
		acciones.add(unaAccion);
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		name = _name;
	}

	public List<CondicionVO> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<CondicionVO> _condiciones) {
		condiciones = _condiciones;
	}

	public List<AccionVO> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionVO> _actuadores) {
		acciones = _actuadores;
	}
	
}
