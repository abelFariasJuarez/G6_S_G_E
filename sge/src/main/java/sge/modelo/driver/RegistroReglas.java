package sge.modelo.driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sge.modelo.dispositivo.Inteligente;
import sge.modelo.regla.Regla;

public class RegistroReglas {

    private Map<Inteligente, List<Regla>> reglas;    
	private static RegistroReglas instancia=null;
	//singleton
	   private RegistroReglas() {
	        reglas= new HashMap<>();
	    }

	    public static RegistroReglas getInstance() {
	        if (instancia == null){
	        	instancia = new RegistroReglas();
	        }
			return instancia;
	    }
	//singleton


	    public void registrarReglas(Inteligente dispositivo, List<Regla> reglas) {
	        this.reglas.put(dispositivo, reglas);
	    }
	    public List<Regla> getReglasParaDispositivo(Inteligente dispositivo) {
	        return this.reglas.get(dispositivo);
	    }
	    
	    
	    
}
