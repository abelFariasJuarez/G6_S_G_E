package sge.modelo.regla;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sge.modelo.Persistible;
import sge.modelo.dispositivo.Inteligente;

@Entity
@Table(name = "Regla")
public class Regla extends Persistible {

	@Column(name = "name")	
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Condicion> condiciones = new ArrayList<Condicion>();
	@OneToMany(cascade = CascadeType.ALL)
	private List<Accion> acciones = new ArrayList<Accion>();
	@ManyToOne
	private Inteligente inteligente;

	public Inteligente getInteligente() {
		return inteligente;
	}

	public void setInteligente(Inteligente dispositivo) {
		this.inteligente = dispositivo;
	}

	public Regla() {
	}
	
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
