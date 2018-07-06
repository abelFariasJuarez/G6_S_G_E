package posicionamiento;

public class Ubicacion {
private Double latitud;
private Double longitud;

public Ubicacion(Double _lati,Double _long) {
latitud=_lati;
longitud=_long;
}
public Double getLatitud() {
	return latitud;
}
public Double getLongitud() {
	return longitud;
}
}
