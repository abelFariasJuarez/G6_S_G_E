package sge.modelo.valueobjects;

public class AccionAhorrroVO extends AccionVO {
	
	public void ejecutar(InteligenteVO dispo) {
		dispo.getDriver().ahorroDeEnergia();
	}
}
