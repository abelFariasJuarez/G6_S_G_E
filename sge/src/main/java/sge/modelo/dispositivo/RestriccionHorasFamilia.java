package sge.modelo.dispositivo;

public enum RestriccionHorasFamilia {

	AIRCONDITIONER(90.0,360.0),

	LAMP(90.0,360.0),

	TV(90.0,360.0),

	WASHINGMACHINE(6.0,30.0),

	COMPUTER(60.0,360.0),

	MICROWAVE(3.0,15.0),

	GRIDDLE(3.0,30.0),

	FAN(120.0,360.0),

	REFRIGERATOR(0.0, Double.MAX_VALUE);

	//Campos tipo constante

    private final double minimo;

    private final double maximo;   

    RestriccionHorasFamilia(double min, double max)

    {

    	minimo = min;

    	maximo = max;

    }    

    public double minimo() { return minimo; }

    public double maximo() { return maximo; }

}
