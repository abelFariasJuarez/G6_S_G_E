package sge.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import sge.dispositivo.Inteligente;
import sge.regla.Sensor;

public class RegistroSensores implements Observer{

	
    private Map<Sensor, List<Inteligente>> dispositivos;    
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


	    public void registrarSensor(Sensor sensor, List<Inteligente> dispositivo) {
	        this.dispositivos.put(sensor, dispositivo);
	    }
	    public List<Inteligente> getDispositivosParaSensor(Sensor sensor) {
	        return this.dispositivos.get(sensor);
	    }
	    
	    
	    public void update(Observable o, Object arg) {
	    	if (o instanceof Sensor) {
	    	           this.handleDispositivoUpdate((Sensor) o);
	    	        }
	    }
	    	
	    	  private void handleDispositivoUpdate(Sensor sensor) {
	    	        List<Inteligente> inteligentes = this.getDispositivosParaSensor(sensor);
	    	        
	    	        inteligentes.stream().forEach(dispo-> RegistroDispositivos.getInstance().accionarReglasDispositivo(dispo));
	  	    	    }
}
