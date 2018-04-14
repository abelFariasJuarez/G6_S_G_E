package sge;

public class Categoria {
	String codigo;
	Float cargoFijo;
	Float cargoVariable;
	Float cotaInferior;
	Float cotaSuperior;

	public boolean tePerteneceEsteCliente(Cliente unCliente) {
		return true;
	}
}
