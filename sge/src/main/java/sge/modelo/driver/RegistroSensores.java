package sge.modelo.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import sge.modelo.dispositivo.Inteligente;
import sge.modelo.regla.Sensor;

public class RegistroSensores implements Observer{

	
    private Map<Sensor, Inteligente> dispositivos;    
	private static RegistroSensores instancia=null;
	//singleton
	   private RegistroSensores() {
	        dispositivos= new HashMap<>();
	    }

	    public static RegistroSensores getInstance() {
	        if (instancia == null){
	        	instancia = new RegistroSensores();
	        }
			return instancia;
	    }
	
	//singleton


	    public void registrarSensor(Sensor sensor, Inteligente dispositivo) {
	        this.dispositivos.put(sensor, dispositivo);
	    }
	    public Inteligente getDispositivoParaSensor(Sensor sensor) {
	        return this.dispositivos.get(sensor);
	    }
	    
	    
	    public void update(Observable o, Object arg) {
	    	if (o instanceof Sensor) {
	    	           this.handleDispositivoUpdate((Sensor) o);
	    	        }
	    }
	    	
	    	  private void handleDispositivoUpdate(Sensor sensor) {
	    	        Inteligente inteligente = this.getDispositivoParaSensor(sensor);
	    	        
	    	        RegistroDispositivos.getInstance().accionarReglasDispositivo(inteligente);
	  	    	    }
}
