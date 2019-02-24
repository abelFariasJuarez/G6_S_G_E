package sge.modelo.valueobjects;

public class AccionPrenderVO extends AccionVO {	

	public void ejecutar(InteligenteVO dispo) {
		dispo.getDriver().prender();
	}
}
