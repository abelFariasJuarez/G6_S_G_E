package sge.driver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import sge.dispositivo.Inteligente;
import sge.regla.Regla;

public class RegistroDispositivos {//Sacar el observer
	private static RegistroDispositivos instance = null;
    private final Set<Inteligente> dispositivos;

    public static RegistroDispositivos getInstance() {
        if (instance == null) {
            instance = new RegistroDispositivos();
        }
        return instance;
    }

    private RegistroDispositivos() {
        this.dispositivos = new HashSet<>();
    }

    public void registrarDispositivo(Inteligente dispositivo) {
        dispositivos.add(dispositivo);
        //agregar que el dispositivo sea observado(cmbios en sus sensores)
    }

    // Se llama cuando un dispositivos se actualizo (medicion de sensores)
    public void accionarReglasDispositivo(Inteligente dispo) {
        List<Regla> reglas = RegistroReglas.getInstance().getReglasParaDispositivo(dispo);

        // disparar las reglas asociadas
        
        reglas.stream().forEach(regla-> regla.accionarSiCorresponde(dispo));
    }
}
