package sge.modelo.valueobjects;

public class AccionApagarVO extends AccionVO {
	public void ejecutar(InteligenteVO dispo) {
		dispo.getDriver().apagar();
	}
}
