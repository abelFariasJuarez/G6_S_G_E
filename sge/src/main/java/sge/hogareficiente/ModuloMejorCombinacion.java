package sge.hogareficiente;

import java.util.List;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import sge.dispositivo.Dispositivo;

public class ModuloMejorCombinacion {
	private double consumoMensualMaximo = 440640;

	public Recomendacion calcularMejorCombinacion(List<Dispositivo> dispositivos) {
			
		Recomendacion reco = new Recomendacion();
		
		Dispositivo[] nodos = new Dispositivo[dispositivos.size()];	
		nodos = dispositivos.toArray(nodos);
				
		SimplexFacade simplexFacade = new SimplexFacade(GoalType.MAXIMIZE, true);
		// 1)
		simplexFacade.crearFuncionEconomica(this.coeficientesFunEco(nodos));

		// 2)
		simplexFacade.agregarRestriccion(Relationship.LEQ, consumoMensualMaximo, this.coeficientesConsumokWh(nodos));

		// 3)
		for (int i = 0; i < nodos.length; i++) {
			this.agregarRestriccionMaxMin(simplexFacade, i, nodos.length, nodos[i]);
		}

		PointValuePair solucion = simplexFacade.resolver();
		
		reco.horasTotalesMensuales(solucion.getValue());	
		
		for (int i = 0; i < nodos.length; i++) {
			reco.agregarTiempoMaximoPorNodo(nodos[i],solucion.getPoint()[i]);
		}	
		
		return reco;		
	}

	private void agregarRestriccionMaxMin(SimplexFacade simplexFacade, int i, int length, Dispositivo nodo) {
		simplexFacade.agregarRestriccion(Relationship.GEQ, nodo.mensualMinimoHoras(), this.coeficientesNodo(i, length));
		simplexFacade.agregarRestriccion(Relationship.LEQ, nodo.mensualMaximoHoras(), this.coeficientesNodo(i, length));
	}

	private double[] coeficientesNodo(int pos, int length) {
		double[] unArray = new double[length];
		for (int i = 0; i < length; i++) {
			unArray[i] = 0;
		}
		unArray[pos] = 1;
		return unArray;
	}

	private double[] coeficientesFunEco(Dispositivo[] arrayDispo) {
		double[] unArray = new double[arrayDispo.length];
		for (int i = 0; i < arrayDispo.length; i++) {
			unArray[i] = 1;
		}
		return unArray;

	}

	private double[] coeficientesConsumokWh(Dispositivo[] arrayDispo) {
		double[] unArray = new double[arrayDispo.length];
		for (int i = 0; i < arrayDispo.length; i++) {
			unArray[i] = arrayDispo[i].getConsumoPorHora();
		}
		return unArray;
	}

}
