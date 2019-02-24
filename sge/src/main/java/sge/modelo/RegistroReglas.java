package sge.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sge.modelo.valueobjects.InteligenteVO;
import sge.modelo.valueobjects.ReglaVO;

public class RegistroReglas {

    private Map<InteligenteVO, List<ReglaVO>> reglas;    
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


	    public void registrarReglas(InteligenteVO dispositivo, List<ReglaVO> reglas) {
	        this.reglas.put(dispositivo, reglas);
	    }
	    public List<ReglaVO> getReglasParaDispositivo(InteligenteVO dispositivo) {
	        return this.reglas.get(dispositivo);
	    }
	    
	    
	    
}
