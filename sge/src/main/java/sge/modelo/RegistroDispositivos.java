package sge.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sge.modelo.valueobjects.InteligenteVO;
import sge.modelo.valueobjects.ReglaVO;

public class RegistroDispositivos {// Sacar el observer
	private static RegistroDispositivos instance = null;
	private final Set<InteligenteVO> dispositivos;

	public static RegistroDispositivos getInstance() {
		if (instance == null) {
			instance = new RegistroDispositivos();
		}
		return instance;
	}

	private RegistroDispositivos() {
		this.dispositivos = new HashSet<>();
	}

	public void registrarDispositivo(InteligenteVO dispositivo) {
		dispositivos.add(dispositivo);
		// agregar que el dispositivo sea observado(cmbios en sus sensores)
	}

	// Se llama cuando un dispositivos se actualizo (medicion de sensores)
	public void accionarReglasDispositivo(InteligenteVO dispo) {
		List<ReglaVO> reglas = RegistroReglas.getInstance().getReglasParaDispositivo(dispo);

		// disparar las reglas asociadas

		reglas.stream().forEach(regla -> regla.accionarSiCorresponde(dispo));
	}
}
